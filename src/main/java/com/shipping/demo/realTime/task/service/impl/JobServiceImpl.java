package com.shipping.demo.realTime.task.service.impl;

import org.apache.pekko.Done;
import org.apache.pekko.NotUsed;
import org.apache.pekko.actor.ActorRef;
import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.cluster.Cluster;
import org.apache.pekko.cluster.pubsub.DistributedPubSub;
import org.apache.pekko.cluster.pubsub.DistributedPubSubMediator;
import org.apache.pekko.cluster.pubsub.DistributedPubSubMediator.Publish;
import org.apache.pekko.stream.CompletionStrategy;
import org.apache.pekko.stream.Materializer;
import org.apache.pekko.stream.OverflowStrategy;
import org.apache.pekko.stream.ThrottleMode;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shipping.demo.realTime.task.actor.PageActor.TaskComplete;
import com.shipping.demo.realTime.task.entity.TaskInfra;
import com.shipping.demo.realTime.task.entity.TaskModel;
import com.shipping.demo.realTime.task.repository.TaskRepository;
import com.shipping.demo.realTime.task.service.JobService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Optional;

import reactor.core.publisher.Flux;

@Component
public class JobServiceImpl implements JobService {

    private static final Logger LOG = LoggerFactory.getLogger(JobServiceImpl.class);

    private final ActorSystem system;
    private final Materializer materializer;
    private final Cluster cluster;
    private final ActorRef pubsub;
    private ActorRef rateLimiter;
    private final TaskRepository taskRepository;

    public JobServiceImpl(ActorSystem system, Materializer materializer, TaskRepository taskRepository) {
        this.system = system;
        this.materializer = materializer;
        this.cluster = Cluster.get(system);
        this.pubsub = DistributedPubSub.get(system).mediator();
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() {
        // Enregistrement des callbacks de cluster
        cluster.registerOnMemberUp(() -> LOG.debug("Member is ready."));
        cluster.registerOnMemberRemoved(() -> {
            LOG.debug("Member is down, stopping actor system.");
            system.terminate();
        });

        Source<DistributedPubSubMediator.Publish, ActorRef> source = Source.actorRef(
        elem -> {
          // complete stream immediately if we send it Done
          if (elem == Done.done()) return Optional.of(CompletionStrategy.immediately());
          else return Optional.empty();
        },
        // never fail the stream because of a message
        elem -> Optional.empty(),
        100000,
        OverflowStrategy.dropHead());


        // Application d'un throttle : 1000 messages par seconde, burst max = 10, mode shaping
        Source<DistributedPubSubMediator.Publish, ActorRef> throttledSource = source.throttle(
                1000,
                Duration.ofSeconds(1),
                10,
                ThrottleMode.shaping()
        );


        // Lancement du stream : le run() retourne l'ActorRef de la source (ici rateLimiter)
        this.rateLimiter = throttledSource.to(Sink.actorRef(pubsub, NotUsed.getInstance())).run(materializer);
    }

    @PreDestroy
    public void shutdown() {
        system.terminate();
    }

    @Override
    public void onTask(TaskInfra task) {
        // Construction du topic et envoi du message Publish contenant TaskComplete
        String topic = "jobs:" + task.getSid();
        rateLimiter.tell(new Publish(topic, new TaskComplete(task)), ActorRef.noSender());
    }

    @Override
    public Flux<TaskModel> getTasks() {
        return taskRepository.findAll();
    }
}

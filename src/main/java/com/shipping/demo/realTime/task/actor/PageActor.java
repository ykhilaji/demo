package com.shipping.demo.realTime.task.actor;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipping.demo.realTime.task.entity.TaskInfra;
import com.shipping.demo.realTime.task.protocol.ActorProtocol;

import org.apache.pekko.actor.AbstractLoggingActor;
import org.apache.pekko.actor.ActorInitializationException;
import org.apache.pekko.actor.ActorKilledException;
import org.apache.pekko.actor.ActorRef;
import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.actor.DeathPactException;
import org.apache.pekko.actor.Props;
import org.apache.pekko.cluster.pubsub.DistributedPubSub;
import org.apache.pekko.cluster.pubsub.DistributedPubSubMediator.Subscribe;
import org.apache.pekko.cluster.pubsub.DistributedPubSubMediator.SubscribeAck;

import org.apache.pekko.stream.Materializer;
import org.apache.pekko.stream.Supervision;
import org.apache.pekko.stream.Supervision.Directive;

import java.util.function.Function;

public class PageActor extends AbstractLoggingActor {

    private final String sid;
    private final ActorRef out;
    private final Materializer materializer;
    private final String topic;


     // Définition du decider pour la supervision du stream
     Function<Throwable, Directive> decider = t -> {
      if (t instanceof ActorInitializationException) {
          log().info("Error during actor initialization", t);
          return Supervision.restart();
      } else if (t instanceof ActorKilledException) {
          log().info("Actor got killed", t);
          return Supervision.stop();
      } else if (t instanceof DeathPactException) {
          log().info("Actor noticed death pact", t);
          return Supervision.stop();
      } else if (t instanceof Exception) {
          log().info("Unexpected exception, restarting", t);
          return Supervision.resume();
      } else {
          log().info("Received throwable", t);
          return Supervision.stop();
      }
  };

    public PageActor(String sid, ActorRef out, ActorSystem system, Materializer materializer) {
        this.sid = sid;
        this.out = out;
        this.materializer = materializer;
        this.topic = "jobs:" + sid;
    }

    public static Props props(String sid, ActorRef out, ActorSystem system, Materializer materializer) {
        return Props.create(PageActor.class, () -> new PageActor(sid, out, system, materializer));
    }

    @Override
    public void preStart() {
        // Récupération du mediator pour le Distributed PubSub
        ActorRef mediator = DistributedPubSub.get(getContext().getSystem()).mediator();
        log().info("Subscribing to {}.", topic);
        mediator.tell(new Subscribe(topic, getSelf()), getSelf());
    }


    @Override
    public void postStop() throws Exception {
        log().info("Page actor {} stopped.", getSelf().path().name());
        super.postStop();   
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TaskComplete.class, t -> {
                    System.out.println("task complete----------------: " + t.tasks.getSid() + ", " + t.tasks.getInfo());
                    ObjectMapper mapper = new ObjectMapper();
                    // Conversion de la tâche en JSON et envoi vers l'acteur 'out'
                    JsonNode json = mapper.valueToTree(t);
                    out.tell(json, getSelf());
                })
                .match(SubscribeAck.class, ack -> {
                    Subscribe subscribe = ack.subscribe();
                    // Vérification que le topic et l'abonné correspondent
                    if (subscribe.topic().equals(topic) && subscribe.ref().equals(getSelf())) {
                        log().info("subscribing");
                    }
                })
                .build();
    }

    public interface Command  extends ActorProtocol{}
    public record TaskComplete(TaskInfra tasks) implements Command {
    }
}

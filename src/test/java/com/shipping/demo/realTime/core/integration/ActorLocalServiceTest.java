package com.shipping.demo.realTime.core.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.pekko.actor.typed.ActorRef;

import com.shipping.demo.realTime.core.PekkoAutoConfiguration;
import com.shipping.demo.realTime.core.integration.SimpleActorBehavior.AskMessageCommand;
import com.shipping.demo.realTime.core.integration.SimpleActorBehavior.StopCommand;
import com.shipping.demo.realTime.core.service.ActorLocalService;

import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.pekko.actor.typed.Behavior;

@TestPropertySource(
        properties = {
                "pekko.name=simple",
                "pekko.actor.provider=local",
                "pekko.actor.provider=cluster",
                "pekko.actor.allow-java-serialization=on",
                "pekko.actor.warn-about-java-serializer-usage=off",
                "pekko.remote.artery.canonical.hostname=127.0.0.1",
                "pekko.remote.artery.canonical.port=40001",
                "pekko.cluster.name=integration-test-cluster",
                "pekko.cluster.seed-nodes=pekko://integration-test-cluster@127.0.0.1:40001,pekko://integration-test-cluster@127.0.0.1:40002,pekko://integration-test-cluster@127.0.0.1:40003",
                "pekko.cluster.downing-provider-class=org.apache.pekko.cluster.sbr.SplitBrainResolverProvider"
        },
        locations = "")
@SpringBootTest(classes = { PekkoAutoConfiguration.class })
public class ActorLocalServiceTest {
    @Autowired
    private ActorLocalService actorLocalService;

    SimpleActorBehavior behavior;
    ActorRef<SimpleActorBehavior.Command> actorRef;

    @BeforeEach
    void setUp() throws Exception {
        behavior = new SimpleActorBehavior();
        actorRef = actorLocalService.createLocalActor("child-actor-" + UUID.randomUUID(), behavior::create, Duration.ofSeconds(1)).toCompletableFuture().get();
    }

    @AfterEach
    void tearDown() {
        actorLocalService.tell(actorRef, new StopCommand());
    }
    
    @Test
    void test_tell() throws Exception {
        SimpleActorBehavior.PrintMessageCommand message = new SimpleActorBehavior.PrintMessageCommand(
                "Hello, World!");
        actorLocalService.tell(actorRef, message);
        Thread.sleep(500); // wait for message to be sent
        assertThat(behavior.getCounterForTest()).isEqualTo(1);
    }

    @Test
    void actors_with_same_path_should_not_be_created() throws Exception {
        final String childName = "same-child-actor-name";
        actorLocalService.createLocalActor(childName, new SimpleActorBehavior()::create, Duration.ofSeconds(1))
                         .toCompletableFuture().get();
        assertThrows(Exception.class, () -> actorLocalService.createLocalActor(childName, new SimpleActorBehavior()::create, Duration.ofSeconds(1)).toCompletableFuture().get());
    }

    @Test
    void test_ask() throws Exception {
        CompletableFuture<String> result = actorLocalService.<SimpleActorBehavior.Command, String>ask(
                actorRef,
                replyTo -> new AskMessageCommand<>("Hello, World!", replyTo),
                Duration.ofSeconds(5)
        ).toCompletableFuture();
        String response = result.get();
        assertThat(response).isEqualTo("Received: Hello, World!");
    }

}


class SimpleActorBehavior {
    public int counterForTest = 0;

    public int getCounterForTest() {
        return counterForTest;
    }

    public interface Command {}

    public record PrintMessageCommand(String message) implements Command {}

    public record AskMessageCommand<T>(String message, ActorRef<T> replyTo) implements Command {}

    public record StopCommand() implements Command {}

    public String beanName() {
        return "system-simple-actor-behavior";
    }

    public Behavior<Command> create() {
      return Behaviors.setup(
              context ->
                      Behaviors.receive(Command.class)
                                .onMessage(PrintMessageCommand.class, this::handlePrintMessageCommand)
                                .onMessage(AskMessageCommand.class, this::handleAskMessageCommand)
                                .onMessage(StopCommand.class, command -> Behaviors.stopped())
                                .build());
    }

    private Behavior<Command> handlePrintMessageCommand(PrintMessageCommand command) {
        counterForTest++;
        return Behaviors.same();
    }

    private Behavior<Command> handleAskMessageCommand(AskMessageCommand<String> command) {
        command.replyTo.tell("Received: " + command.message);
        return Behaviors.same();
    }
}

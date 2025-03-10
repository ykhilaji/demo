package com.shipping.demo.realTime.simple.service;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import com.shipping.demo.realTime.core.service.ActorLocalService;
import com.shipping.demo.realTime.simple.behaviors.WorldBehavior;
import com.shipping.demo.realTime.simple.behaviors.WorldBehavior.AskMessageCommand;
import org.apache.pekko.actor.typed.ActorRef;

@Service
public class GreetingService {
    @Autowired
    private ActorLocalService actorLocalService;
    private ActorRef<WorldBehavior.Command> actorRef;
    private WorldBehavior behavior;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        try {
            behavior = new WorldBehavior();
            actorRef = actorLocalService.createLocalActor(
                "child-actor-" + UUID.randomUUID(),
                behavior::create,
                Duration.ofSeconds(5)
            ).toCompletableFuture().join();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize actor system", e);
        }
    }

    public CompletableFuture<String> hello() {
        CompletableFuture<String> result = actorLocalService.<WorldBehavior.Command, String>ask(
            actorRef,
            replyTo -> new AskMessageCommand<>("Hello, World!", replyTo),
            Duration.ofSeconds(5)
        ).toCompletableFuture();

        return result;
    }
}
package com.shipping.demo.realTime.task.actor;

import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.Props;

public class MyActor extends AbstractActor {

    public static Props props() {
        return Props.create(MyActor.class, MyActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    System.out.println("Actor received: " + msg);
                    // Répond avec une chaîne traitée
                    getSender().tell("Processed: " + msg, getSelf());
                })
                .build();
    }
}
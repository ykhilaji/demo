package com.shipping.demo.realTime.core.integration;

import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.springframework.stereotype.Component;

import com.shipping.demo.realTime.core.behaviors.SingletonBehavior;
import com.shipping.demo.realTime.core.integration.SimpleActorBehavior.Command;

// for testing purposes
@Component
public class SingletonBehaviorImplementation implements SingletonBehavior<Command> {

	public static final String BEAN_NAME = "system-singleton-behavior-implementation";

	interface Command {}

    @Override
    public String beanName() {
        return BEAN_NAME;
    }

    @Override
    public Behavior<com.shipping.demo.realTime.core.integration.SimpleActorBehavior.Command> create() {
        return Behaviors.stopped();
    };

	
}
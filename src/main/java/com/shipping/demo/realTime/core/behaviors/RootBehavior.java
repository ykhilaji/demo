package com.shipping.demo.realTime.core.behaviors;

import org.apache.pekko.actor.typed.Behavior;

import com.shipping.demo.realTime.core.DependencyContainer;

public interface RootBehavior {
	Behavior<String> create(DependencyContainer container);
}
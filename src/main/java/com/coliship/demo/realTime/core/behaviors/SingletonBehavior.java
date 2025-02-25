package com.coliship.demo.realTime.core.behaviors;

import org.apache.pekko.actor.typed.Behavior;

public interface SingletonBehavior<T> {
    /** Should not collide with existing bean names. */
	String beanName();

	Behavior<T> create();
    
}

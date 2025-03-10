package com.shipping.demo.realTime.core;

import org.apache.pekko.actor.typed.ActorRef;

public interface ActorRefWrapper<T> {
	String beanName();

	ActorRef<T> unwrap();
}
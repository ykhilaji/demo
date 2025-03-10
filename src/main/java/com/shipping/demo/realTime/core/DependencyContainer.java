package com.shipping.demo.realTime.core;

import java.util.List;


import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

import com.shipping.demo.realTime.core.behaviors.RootBehavior;
import com.shipping.demo.realTime.core.behaviors.SingletonBehavior;

@Component
public class DependencyContainer {
	private final GenericApplicationContext applicationContext;
	private final RootBehavior rootBehavior;
	private final List<SingletonBehavior<?>> singletonBehaviors;
	public DependencyContainer(
            GenericApplicationContext applicationContext,
            RootBehavior rootBehavior,
            List<SingletonBehavior<?>> singletonBehaviors) {
		this.applicationContext = applicationContext;
		this.rootBehavior = rootBehavior;
		this.singletonBehaviors = singletonBehaviors;
    }

	public GenericApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public RootBehavior getRootBehavior() {
		return rootBehavior;
	}

	public List<SingletonBehavior<?>> getSingletonBehaviors() {
		return singletonBehaviors;
	}
}

package com.shipping.demo.realTime.core;

import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.stream.Materializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassicActorSystemConfiguration {

    @Bean
    public ActorSystem classicActorSystem(org.apache.pekko.actor.typed.ActorSystem<?> typedSystem) {
        return typedSystem.classicSystem();
    }

    @Bean
    public Materializer materializer(ActorSystem system) {
        return Materializer.createMaterializer(system);
    }
}
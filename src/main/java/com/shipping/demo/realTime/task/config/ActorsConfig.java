package com.shipping.demo.realTime.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.shipping.demo.realTime.task.actor.MessageProcessor;
import com.shipping.demo.realTime.task.converter.JacksonConfig;

//@Configuration
public class ActorsConfig {
    
    // @Autowired
    // private JacksonConfig jacksonConfig;
    
    // @Bean
    // @Primary
    // public MessageProcessor messageProcessor() {
    //     MessageProcessor processor = new MessageProcessor();
    //     processor.setJacksonConfig(jacksonConfig);
    //     return processor;
    // }
}

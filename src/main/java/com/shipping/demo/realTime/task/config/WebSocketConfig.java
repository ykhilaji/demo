package com.shipping.demo.realTime.task.config;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import org.apache.pekko.actor.ActorRef;
import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Adapter;
import org.apache.pekko.stream.Materializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.WebSocketHandler;
import com.shipping.demo.realTime.core.service.ActorLocalService;
import com.shipping.demo.realTime.task.actor.MessageProcessor;
import com.shipping.demo.realTime.task.actor.MessageProcessor.JsonResponse;
import com.shipping.demo.realTime.task.actor.MessageProcessor.ProcessJsonMessage;
import com.shipping.demo.realTime.task.converter.JacksonConfig;

import reactor.core.publisher.Mono;
import org.apache.pekko.pattern.Patterns;

@Configuration
public class WebSocketConfig {
    private final ActorLocalService actorLocalService;
    private final JacksonConfig jacksonConfig;

    public WebSocketConfig( ActorLocalService actorLocalService, JacksonConfig jacksonConfig) {
        this.actorLocalService = actorLocalService;
        this.jacksonConfig = jacksonConfig;
    }
    

    @Bean
    public WebSocketHandler webSocketHandler() {
        return session -> {
            return session.receive()
                .flatMap(webSocketMessage -> {
                    String payload = webSocketMessage.getPayloadAsText();
                    
                    try {
                        // Create a new MessageProcessor instance for each request
                        MessageProcessor behavior = new MessageProcessor();
                        // Set the JacksonConfig
                        behavior.setJacksonConfig(jacksonConfig);
                        
                        // Create a unique actor for each message processing
                        org.apache.pekko.actor.typed.ActorRef<MessageProcessor.Command> actorRef = 
                            actorLocalService.createLocalActor(
                                "message-processor-" + UUID.randomUUID(), 
                                behavior::create, 
                                Duration.ofSeconds(1)
                            ).toCompletableFuture().get();
                        
                        // Convert to classic ActorRef for compatibility with Patterns.ask
                        ActorRef classicActorRef = Adapter.toClassic(actorRef);
                        
                        // Ask the actor to process the message with a timeout
                        CompletionStage<Object> askFuture = Patterns.ask(
                            classicActorRef,
                            new ProcessJsonMessage(payload, null),
                            Duration.ofSeconds(3)
                        );

                        // Handle the response
                        return Mono.fromFuture(askFuture.toCompletableFuture())
                            .map(response -> {
                                if (response instanceof JsonResponse) {
                                    return ((JsonResponse) response).json();
                                } else {
                                    return "Error: Unexpected response type";
                                }
                            })
                            .flatMap(responseText -> 
                                session.send(Mono.just(session.textMessage(responseText)))
                            );
                    } catch (InterruptedException | ExecutionException e) {
                        return Mono.error(e);
                    }
                })
                .then();
        };
    }
}

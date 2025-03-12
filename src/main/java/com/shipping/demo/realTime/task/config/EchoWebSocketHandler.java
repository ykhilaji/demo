package com.shipping.demo.realTime.task.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Simple echo WebSocket handler that echoes back any received message
 */
@Component
public class EchoWebSocketHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        // Flux de messages entrants, transformés et renvoyés au client.
        Flux<WebSocketMessage> output = session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .map(message -> "Echo: " + message.toUpperCase())
            .map(session::textMessage);
        System.out.println("webcoket");
        // Envoi du flux transformé vers le client de manière non bloquante.
        return session.send(output);
    }
      
}
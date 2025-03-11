package com.shipping.demo.realTime.task.actor;

import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.Props;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.databind.JsonNode;

public class WebSocketSender extends AbstractActor {
    private final WebSocketSession session;

    public WebSocketSender(WebSocketSession session) {
        this.session = session;
    }

    public static Props props(WebSocketSession session) {
        return Props.create(WebSocketSender.class, () -> new WebSocketSender(session));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(JsonNode.class, this::handleJsonMessage)
                .build();
    }

    private void handleJsonMessage(JsonNode json) {
        WebSocketMessage message = session.textMessage(json.toString());
        session.send(reactor.core.publisher.Mono.just(message)).subscribe();
    }
}
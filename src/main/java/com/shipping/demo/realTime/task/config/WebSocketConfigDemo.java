package com.shipping.demo.realTime.task.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

@Configuration
@EnableWebFlux
public class WebSocketConfigDemo {
    
    @Autowired
    private WebSocketHandler webSocketHandler;

        // @Bean
        // public SimpleUrlHandlerMapping webSocketHandlerMapping(EchoWebSocketHandler echoWebSocketHandler) {
        //     Map<String, Object> urlMap = new HashMap<>();
        //     urlMap.put("/ws/echo", echoWebSocketHandler);
        //     SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        //     mapping.setUrlMap(urlMap);
        //     mapping.setOrder(-1); // Priorité élevée
        //     return mapping;
        // }

    @Bean
    public HandlerMapping webSocketHandlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/ws/echo", webSocketHandler);

        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(map);
        return handlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}

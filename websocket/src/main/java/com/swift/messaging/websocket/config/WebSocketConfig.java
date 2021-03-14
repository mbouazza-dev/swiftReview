package com.swift.messaging.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    private Integer findRandomOpenPortOnAllLocalInterfaces() throws IOException {
        try (
                ServerSocket socket = new ServerSocket(0);
        ) {
            return socket.getLocalPort();

        }
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");

        // Use this for enabling a Full featured broker like RabbitMQ
            registry.enableStompBrokerRelay("/topic")
                    .setRelayHost("localhost")
                    .setClientLogin("guest")
                    .setClientPasscode("guest");
    }

}


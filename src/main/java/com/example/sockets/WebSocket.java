package com.example.sockets;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.Application;
import com.example.models.Message;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableWebSocket
public class WebSocket implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(@SuppressWarnings("null") WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler(), "/ws/messages").setAllowedOrigins("*");
    }

    @Bean
    MessageWebSocketHandler messageWebSocketHandler() {
        return new MessageWebSocketHandler();
    }

    @Component
    public static class MessageWebSocketHandler extends TextWebSocketHandler {
        private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

        @Override
        public void afterConnectionEstablished(@SuppressWarnings("null") WebSocketSession session) throws Exception {
            sessions.add(session);
        }

        @Override
        public void afterConnectionClosed(@SuppressWarnings("null") WebSocketSession session,
                @SuppressWarnings("null") CloseStatus status) throws Exception {
            sessions.remove(session);
        }

        public void broadcastMessage(Message message) throws Exception {
            String jsonMessage = Application.GSON.toJson(message);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(jsonMessage));
                }
            }
        }
    }
}
package io.github.teamtracker.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.Application;
import io.github.teamtracker.model.Message;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
@EnableWebSocket
public class WebSocket implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/ws").setAllowedOrigins("*");
    }

    @Primary
    @Bean
    WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }

    @Component
    public static class WebSocketHandler extends TextWebSocketHandler {

        /**
         * The list of active WebSocket sessions.
         */
        private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

        @Autowired
        private ChatRepository chatRepository;

        /**
         * Add the WebSocket session to the list of active sessions.
         */
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            sessions.add(session);

            String message = "{\"sessionId\": \"" + session.getId() + "\"}";

            session.sendMessage(new TextMessage(message));

            // When connecting to the WebSocket, the user is not yet known.
            // Send the sessionId to the client - the client will send it back with the user
            // information.
        }

        /**
         * Remove the WebSocket session from the list of active sessions.
         */
        @Override
        public void afterConnectionClosed(WebSocketSession session,
                CloseStatus status) throws Exception {
            sessions.remove(session);
        }

        /**
         * Register a user with the WebSocket session.
         * 
         * @param userId the ID of the user
         */
        public void registerUser(Integer userId, String sessionId) {
            sessions.stream().filter(session -> session.getId().equals(sessionId)).findFirst().get()
                    .getAttributes().put("user", userId);
        }

        /**
         * Broadcast a message to all connected WebSocket sessions.
         * 
         * @param message the message to broadcast
         * @throws Exception if the message cannot be sent
         */
        public void broadcastMessage(Message message) throws Exception {
            String jsonMessage = Application.GSON.toJson(message);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {

                    if (session.getAttributes().containsKey("user")) {
                        Integer userId = (Integer) session.getAttributes().get("user");

                        chatRepository.findByUserId(userId).forEach(chat -> {
                            if (chat.getChatGroupId().equals(message.getChatGroupId())) {
                                try {
                                    session.sendMessage(new TextMessage(jsonMessage));
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }
    }
}
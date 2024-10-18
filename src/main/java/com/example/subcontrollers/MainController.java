package com.example.subcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.configurations.WebSocketConfig.MessageWebSocketHandler;
import com.example.models.Chat;
import com.example.models.Message;
import com.example.repositories.ChatRepository;
import com.example.repositories.MessageRepository;
import com.example.repositories.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private MessageWebSocketHandler messageWebSocketHandler;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/send")
    public @ResponseBody String postMessage(@RequestParam Integer user_id, @RequestParam Integer chat_id,
            @RequestParam String text) throws Exception {
        Message message = new Message();
        message.setUser_id(user_id);
        message.setChat_id(chat_id);
        message.setText(text);
        messageRepository.save(message);

        messageWebSocketHandler.broadcastMessage(message);

        return message.getId().toString();
    }

    @GetMapping(path = "/resolveUsername")
    public @ResponseBody String getUsername(@RequestParam Integer id) {
        Iterable<String> usernames = userRepository.resolveUsername(id);
        return usernames.iterator().next();
    }

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<Chat> getChats(@RequestParam Integer user_id) {
        return chatRepository.findByUser_id(user_id);
    }
}
package com.example.subcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.configurations.WebSocketConfig.MessageWebSocketHandler;
import com.example.models.Message;
import com.example.repositories.MessageRepository;


import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    private MessageWebSocketHandler messageWebSocketHandler;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping(path = "/send")
    public @ResponseBody String postMessage(@RequestParam Integer user_id, @RequestParam Integer chat_id,
            @RequestParam String text) throws Exception {
        Message message = new Message();
        message.setUser_id(user_id);
        message.setChat_id(chat_id);
        message.setText(text);

        messageRepository.save(message);

        messageWebSocketHandler.broadcastMessage(text);

        return message.getId().toString();
    }
}
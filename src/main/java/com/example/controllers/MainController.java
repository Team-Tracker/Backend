package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.Chat;
import com.example.models.Message;
import com.example.models.User;
import com.example.repositories.ChatRepository;
import com.example.repositories.MessageRepository;
import com.example.repositories.UserRepository;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping(path = "/send")
    public @ResponseBody String sendMessage(@RequestParam Integer src_id, @RequestParam Integer dst_id,
            @RequestParam String text) {
        Message message = new Message();
        message.setUser_id(src_id);
        message.setText(text);

        messageRepository.save(message);

        Chat chat = new Chat();

        chat.setUser_id(dst_id);
        chat.setMessage_id(message.getId());

        chatRepository.save(chat);

        return "Sent";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<Chat> getChats(@RequestParam Integer user_id) {
        return chatRepository.findByUser_id(user_id);
    }

    @GetMapping(path = "/messages")
    public @ResponseBody Iterable<Message> getMessages(@RequestParam Integer user_id) {
        // select * from messages where user_id = user_id

        Iterable<Chat> chats = chatRepository.findByUser_id(user_id);

        ArrayList<Message> messages = new ArrayList<Message>();

        for (Chat chat : chats) {
            
        }

        Iterable<Message> messages = messageRepository.findByMessage_id();

        return messages;

    }
}
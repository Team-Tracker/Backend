package com.example.controllers;

import java.util.ArrayList;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.helpers.ChatHelper;
import com.example.models.Chat;
import com.example.models.ChatGroup;
import com.example.models.Message;
import com.example.repositories.ChatGroupRepository;
import com.example.repositories.ChatRepository;
import com.example.repositories.MessageRepository;
import com.example.sockets.WebSocket.WebSocketHandler;

import java.util.List;

@Controller
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private WebSocketHandler messageWebSocketHandler;

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @PostMapping(path = "/register")
    public @ResponseBody Integer register(@RequestParam Integer userId, @RequestParam Integer sessionId) throws Exception {
        messageWebSocketHandler.registerUser(userId, sessionId);

        return sessionId;
    }

    @PostMapping(path = "/send")
    public @ResponseBody String postMessage(@RequestParam Integer user_id, @RequestParam Integer chat_id,
            @RequestParam String text) throws Exception {
        Message message = new Message();

        message.setUser_id(user_id);
        message.setChatGroupId(chat_id);
        message.setText(text);
        
        messageRepository.save(message);

        messageWebSocketHandler.broadcastMessage(message);

        return message.getId().toString();
    }

    @PostMapping(path = "/create")
    public @ResponseBody Integer createChat(@RequestParam Integer user_id, @RequestParam Integer other_user_id) {
        int id = ChatHelper.createChatGroup(user_id, other_user_id, chatGroupRepository);

        ChatHelper.createChat(user_id, id, chatRepository);
        ChatHelper.createChat(other_user_id, id, chatRepository);

        return id;
    }

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<ChatGroup> getChats(@RequestParam Integer user_id) {
        Iterable<Chat> chats = chatRepository.findByUserId(user_id);

        List<ChatGroup> chatGroups = new ArrayList<>();

        for (Chat chat : chats) {
            System.out.println(chat.getChatGroupId());
            chatGroups.add(chatGroupRepository.findById(chat.getChatGroupId()).get());
        }

        return chatGroups;
    }
}
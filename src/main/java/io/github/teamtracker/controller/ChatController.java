package io.github.teamtracker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.chat.Chat;
import io.github.teamtracker.model.chat.ChatGroup;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.socket.WebSocket.WebSocketHandler;
import io.github.teamtracker.utility.ChatHelper;

import java.util.List;

@Controller
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private WebSocketHandler messageWebSocketHandler;

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<ChatGroup> getChats(@RequestParam Integer userId) {
        Iterable<Chat> chats = this.chatRepository.findByUserId(userId);

        List<ChatGroup> chatGroups = new ArrayList<>();

        for (Chat chat : chats) {
            chatGroups.add(this.chatGroupRepository.findById(chat.getChatGroupId()).get());
        }

        return chatGroups;
    }

    @PostMapping(path = "/createSingle")
    public @ResponseBody Integer createChat(@RequestParam Integer userId) {
        int id = ChatHelper.createChatGroup(this.chatGroupRepository);

        ChatHelper.createChat(userId, id, this.chatRepository);

        return id;
    }

    @PostMapping(path = "/createMulti")
    public @ResponseBody Integer createChat(@RequestParam Integer userId, @RequestParam Integer otherUserId) {
        int id = ChatHelper.createChatGroup(userId, otherUserId, this.chatGroupRepository);

        ChatHelper.createChat(userId, id, this.chatRepository);
        ChatHelper.createChat(otherUserId, id, this.chatRepository);

        return id;
    }

    @PostMapping(path = "/register")
    public @ResponseBody String register(@RequestParam Integer userId, @RequestParam String sessionId)
            throws Exception {
        this.messageWebSocketHandler.registerUser(userId, sessionId);

        return sessionId;
    }
}
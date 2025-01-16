package io.github.teamtracker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.Chat;
import io.github.teamtracker.model.ChatGroup;
import io.github.teamtracker.model.Message;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.repository.MessageRepository;
import io.github.teamtracker.socket.WebSocket.WebSocketHandler;
import io.github.teamtracker.utility.ChatHelper;

import java.util.List;

@Controller
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private WebSocketHandler messageWebSocketHandler;

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatRepository chatRepository;

    @PostMapping(path = "/register")
    public @ResponseBody Integer register(@RequestParam Integer userId, @RequestParam Integer sessionId)
            throws Exception {
        messageWebSocketHandler.registerUser(userId, sessionId);

        return sessionId;
    }

    @PostMapping(path = "/create")
    public @ResponseBody Integer createChat(@RequestParam Integer userId, @RequestParam Integer other_user_id) {
        int id = ChatHelper.createChatGroup(userId, other_user_id, chatGroupRepository);

        ChatHelper.createChat(userId, id, chatRepository);
        ChatHelper.createChat(other_user_id, id, chatRepository);

        return id;
    }

    @PostMapping(path = "/createSingle")
    public @ResponseBody Integer createChat(@RequestParam Integer userId) {
        int id = ChatHelper.createChatGroup(userId, chatGroupRepository);

        ChatHelper.createChat(userId, id, chatRepository);

        return id;
    }

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<ChatGroup> getChats(@RequestParam Integer userId) {
        Iterable<Chat> chats = chatRepository.findByUserId(userId);

        List<ChatGroup> chatGroups = new ArrayList<>();

        for (Chat chat : chats) {
            chatGroups.add(chatGroupRepository.findById(chat.getChatGroupId()).get());
        }

        return chatGroups;
    }
}
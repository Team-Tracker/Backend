package io.github.teamtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.chat.Chat;
import io.github.teamtracker.model.chat.ChatGroup;
import io.github.teamtracker.model.team.TeamChat;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.repository.TeamChatRepository;
import io.github.teamtracker.socket.WebSocket.WebSocketHandler;
import io.github.teamtracker.utility.ChatHelper;

@Controller
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private TeamChatRepository teamChatRepository;

    @Autowired
    private WebSocketHandler messageWebSocketHandler;

    @GetMapping(path = "/")
    public @ResponseBody Iterable<ChatGroup> getChats() {
        return this.chatGroupRepository.findAll();
    }

    @GetMapping(path = "/chats")
    public @ResponseBody Iterable<ChatGroup> getChats(@RequestParam Integer userId) {
        Iterable<Chat> chats = this.chatRepository.findByUserId(userId);
        Iterable<TeamChat> teamChats = this.teamChatRepository.findAll();

        List<ChatGroup> chatGroups = new ArrayList<>();

        for (Chat chat : chats) {
            chatGroups.add(this.chatGroupRepository.findById(chat.getChatGroupId()).get());
        }

        for (TeamChat teamChat : teamChats) {
            for (ChatGroup chatGroup : chatGroups) {
                if (teamChat.getChatGroupId() == chatGroup.getId()) {
                    chatGroups.remove(chatGroups.indexOf(chatGroup));
                }
            }
        }

        return chatGroups;
    }

    @PostMapping(path = "/createMono")
    public @ResponseBody Integer createChat(@RequestParam Integer userId) {
        int id = ChatHelper.createChatGroup(this.chatGroupRepository);

        ChatHelper.createChat(userId, id, this.chatRepository);

        return id;
    }

    @PostMapping(path = "/createDuo")
    public @ResponseBody Integer createChat(@RequestParam Integer userId, @RequestParam Integer otherUserId) {
        int id = ChatHelper.createChatGroup(userId, otherUserId, this.chatGroupRepository);

        ChatHelper.createChat(userId, id, this.chatRepository);
        ChatHelper.createChat(otherUserId, id, this.chatRepository);

        return id;
    }

    @PostMapping(path = "/add")
    public @ResponseBody Integer add(@RequestParam Integer chatGroupId, @RequestParam Integer userId) {
        int id = ChatHelper.createChat(userId, chatGroupId, this.chatRepository);

        return id;
    }

    @PostMapping(path = "/register")
    public @ResponseBody String register(@RequestParam Integer userId, @RequestParam String sessionId)
            throws Exception {
        this.messageWebSocketHandler.registerUser(userId, sessionId);

        return sessionId;
    }

    @PatchMapping(path = "/rename")
    public @ResponseBody String rename(@RequestParam Integer chatGroupId, @RequestParam String name) {
        ChatGroup chatGroup = this.chatGroupRepository.findById(chatGroupId).get();

        chatGroup.setName(name);

        this.chatGroupRepository.save(chatGroup);

        return name;
    }
}
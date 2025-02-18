package io.github.teamtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.teamtracker.model.chat.ChatGroup;
import io.github.teamtracker.service.TeamChatService;

@RestController
@RequestMapping("/teamChat")
public class TeamChatController {

    private final TeamChatService teamChatService;

    public TeamChatController(TeamChatService teamChatService) {
        this.teamChatService = teamChatService;
    }

    @GetMapping("/{id}")
    public ChatGroup getChat(@PathVariable Integer teamId) {
        return this.teamChatService.getChat(teamId);
    }
}
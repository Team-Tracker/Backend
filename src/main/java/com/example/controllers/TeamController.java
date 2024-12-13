package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.Chat;
import com.example.models.Message;
import com.example.models.Team;
import com.example.repositories.ChatRepository;
import com.example.repositories.MessageRepository;
import com.example.repositories.TeamRepository;
import com.example.sockets.WebSocket.WebSocketHandler;

@Controller
@RequestMapping(path = "/team")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping(path = "/my")
    public @ResponseBody Iterable<Team> getTeams(@RequestParam Integer user_id) {
        return this.teamRepository.findByUser_id(user_id);
    }
}
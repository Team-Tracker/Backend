package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.MessageRepository;
import io.github.teamtracker.repository.TeamRepository;
import io.github.teamtracker.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/reset")
    public @ResponseBody int resetDatabase() {
        this.chatGroupRepository.deleteAll();
        this.chatRepository.deleteAll();
        this.memberRepository.deleteAll();
        this.messageRepository.deleteAll();
        this.teamRepository.deleteAll();
        this.userRepository.deleteAll();

        return 0;
    }
}
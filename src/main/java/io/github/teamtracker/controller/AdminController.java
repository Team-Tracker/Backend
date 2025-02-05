package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.user.User;
import io.github.teamtracker.repository.CalenderRepository;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.MessageRepository;
import io.github.teamtracker.repository.TeamRepository;
import io.github.teamtracker.repository.UserRepository;
import io.github.teamtracker.utility.ChatHelper;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private CalenderRepository calenderRepository;

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

    @GetMapping(path = "/defaults")
    public @ResponseBody int defaults() {
        // Create users

        User lorenz = new User();

        lorenz.setUsername("lorenz");
        lorenz.setPassword("lorenz");

        this.userRepository.save(lorenz);

        User kurt = new User();

        kurt.setUsername("kurt");
        kurt.setPassword("kurt");

        this.userRepository.save(kurt);

        User suad = new User();

        suad.setUsername("suad");
        suad.setPassword("suad");

        this.userRepository.save(suad);

        User stefan = new User();

        stefan.setUsername("stefan");
        stefan.setPassword("stefan");

        this.userRepository.save(stefan);

        // Create chat

        int id = ChatHelper.createChatGroup(lorenz.getId(), kurt.getId(), this.chatGroupRepository);

        ChatHelper.createChat(lorenz.getId(), id, this.chatRepository);
        ChatHelper.createChat(kurt.getId(), id, this.chatRepository);

        // Create chat group for all

        id = ChatHelper.createChatGroup(this.chatGroupRepository);

        ChatHelper.createChat(lorenz.getId(), id, this.chatRepository);
        ChatHelper.createChat(kurt.getId(), id, this.chatRepository);
        ChatHelper.createChat(suad.getId(), id, this.chatRepository);
        ChatHelper.createChat(stefan.getId(), id, this.chatRepository);

        return 0;
    }

    @GetMapping(path = "/zero")
    public @ResponseBody int zero() {
        this.calenderRepository.deleteAll();
        this.chatGroupRepository.deleteAll();
        this.chatRepository.deleteAll();
        this.memberRepository.deleteAll();
        this.messageRepository.deleteAll();
        this.teamRepository.deleteAll();
        this.userRepository.deleteAll();

        return 0;
    }

    @GetMapping(path = "/reset")
    public @ResponseBody int resetDatabase() {
        this.calenderRepository.deleteAll();
        this.chatGroupRepository.deleteAll();
        this.chatRepository.deleteAll();
        this.memberRepository.deleteAll();
        this.messageRepository.deleteAll();
        this.teamRepository.deleteAll();
        this.userRepository.deleteAll();

        return this.resetDatabase();
    }
}
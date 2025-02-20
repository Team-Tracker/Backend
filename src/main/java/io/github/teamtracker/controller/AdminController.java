package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.calender.Event;
import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.model.team.Team;
import io.github.teamtracker.model.team.TeamChat;
import io.github.teamtracker.model.user.User;
import io.github.teamtracker.repository.CalenderRepository;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.ChatRepository;
import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.MessageRepository;
import io.github.teamtracker.repository.TeamChatRepository;
import io.github.teamtracker.repository.TeamRepository;
import io.github.teamtracker.repository.UserRepository;
import io.github.teamtracker.repository.scrum.AssigneeRepository;
import io.github.teamtracker.repository.scrum.BoardRepository;
import io.github.teamtracker.repository.scrum.SprintRepository;
import io.github.teamtracker.repository.scrum.TaskRepository;
import io.github.teamtracker.utility.ChatHelper;

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
    private TeamChatRepository teamChatRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/zero")
    public @ResponseBody int zero() {
        this.calenderRepository.deleteAll();
        this.chatGroupRepository.deleteAll();
        this.chatRepository.deleteAll();
        this.memberRepository.deleteAll();
        this.messageRepository.deleteAll();
        this.teamChatRepository.deleteAll();
        this.teamRepository.deleteAll();
        this.userRepository.deleteAll();

        this.resetScrum();

        return 0;
    }

    @GetMapping(path = "/reset")
    public @ResponseBody int resetDatabase() {
        this.zero();

        return this.defaults();
    }

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

        // Calender

        Event event = new Event();

        event.setUserId(lorenz.getId());
        event.setEventName("Meeting");
        event.setEventDescription("Team Tracker Meeting");
        event.setEventDate("2025-02-28");
        event.setStartTime("12:00");
        event.setEndTime("1:00");

        this.calenderRepository.save(event);

        event.setUserId(kurt.getId());

        this.calenderRepository.save(event);

        event.setUserId(suad.getId());

        this.calenderRepository.save(event);

        event.setUserId(stefan.getId());

        this.calenderRepository.save(event);

        // Team

        Team team = new Team();

        team.setName("Team Tracker");
        team.setDescription("Team Tracker Development Team");
        team.setCreatorId(lorenz.getId());

        this.teamRepository.save(team);

        Integer chatGroupId = ChatHelper.createChatGroup(this.chatGroupRepository);

        TeamChat teamChat = new TeamChat();

        teamChat.setTeamId(team.getId());
        teamChat.setChatGroupId(chatGroupId);

        this.teamChatRepository.save(teamChat);

        Member member = new Member();

        member.setUserId(lorenz.getId());
        member.setTeamId(team.getId());
        member.setRole("admin");

        this.memberRepository.save(member);

        member = new Member();

        member.setUserId(kurt.getId());
        member.setTeamId(team.getId());
        member.setRole("admin");

        this.memberRepository.save(member);

        member = new Member();

        member.setUserId(suad.getId());
        member.setTeamId(team.getId());
        member.setRole("admin");

        this.memberRepository.save(member);

        member = new Member();

        member.setUserId(stefan.getId());
        member.setTeamId(team.getId());
        member.setRole("admin");

        this.memberRepository.save(member);

        return 0;
    }

    @Autowired
    private AssigneeRepository assigneeRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private TaskRepository taskRepository;

    private void resetScrum() {
        this.assigneeRepository.deleteAll();
        this.boardRepository.deleteAll();
        this.sprintRepository.deleteAll();
        this.taskRepository.deleteAll();
    }
}
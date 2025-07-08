package io.github.teamtracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.model.chat.ChatGroup;
import io.github.teamtracker.model.team.TeamChat;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.TeamChatRepository;

@Service
@Transactional
public class TeamChatService {

    private final TeamChatRepository teamChatRepository;
    private final ChatGroupRepository chatGroupRepository;

    public TeamChatService(TeamChatRepository teamChatRepository, ChatGroupRepository chatGroupRepository) {
        this.teamChatRepository = teamChatRepository;
        this.chatGroupRepository = chatGroupRepository;
    }

    public ChatGroup getChat(Integer teamId) {
        TeamChat teamChat = this.teamChatRepository.findByTeamId(teamId);

        if (teamChat == null) {
            throw new RuntimeException("Team not found");
        }

        ChatGroup chatGroup = this.chatGroupRepository.findById(teamChat.getChatGroupId()).orElse(null);

        return chatGroup;
    }
}
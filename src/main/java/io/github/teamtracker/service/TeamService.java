package io.github.teamtracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.exception.TeamException;
import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.model.team.Team;
import io.github.teamtracker.model.team.TeamChat;
import io.github.teamtracker.repository.ChatGroupRepository;
import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.TeamChatRepository;
import io.github.teamtracker.repository.TeamRepository;
import io.github.teamtracker.utility.ChatHelper;

@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    private final TeamChatRepository teamChatRepository;
    private final ChatGroupRepository chatGroupRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository,
            TeamChatRepository teamChatRepository, ChatGroupRepository chatGroupRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.teamChatRepository = teamChatRepository;
        this.chatGroupRepository = chatGroupRepository;
    }

    public Iterable<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }

    public List<Team> getTeams(Integer userId) {
        List<Team> teams = new ArrayList<>();

        Iterable<Member> members = this.memberRepository.findByUserId(userId);

        for (Member member : members) {
            Integer teamId = member.getTeamId();

            Optional<Team> team = this.teamRepository.findById(teamId);

            if (team != null) {
                teams.add(team.get());
            }
        }

        return teams;
    }

    public Optional<Team> getTeamById(Integer id) {
        return this.teamRepository.findById(id);
    }

    public Team createTeam(Team team) {
        // ! Check if creatorId User exists
        Team newTeam = new Team();

        Integer creatorId = team.getCreatorId();

        newTeam.setCreatorId(creatorId);
        newTeam.setName(team.getName());
        newTeam.setDescription(team.getDescription());

        this.teamRepository.save(newTeam);

        Integer teamId = newTeam.getId();

        this.newTeam(teamId, creatorId);
        this.newChat(teamId);

        return this.teamRepository.save(newTeam);
    }

    public void deleteTeam(Integer id) {
        this.teamRepository.deleteById(id);

        this.removeTeam(id);
    }

    public Member addUserToTeam(Integer teamId, Integer userId) {
        this.teamRepository.findById(teamId).orElseThrow(() -> new TeamException("Team not found"));

        if (this.memberRepository.findByUserIdAndTeamId(userId, teamId) != null) {
            new TeamException("User already in team");
        }

        Member member = new Member(userId, teamId, "member");

        return this.memberRepository.save(member);
    }

    public void removeUserFromTeam(Integer teamId, Integer userId) {
        Member member = this.memberRepository.findByUserIdAndTeamId(userId, teamId);

        if (member == null) {
            throw new TeamException("Member not found");
        }

        this.memberRepository.delete(member);
    }

    private Member newTeam(Integer teamId, Integer userId) {
        Member member = new Member(userId, teamId, "admin");

        return this.memberRepository.save(member);
    }

    private TeamChat newChat(Integer teamId) {
        Integer chatGroupId = ChatHelper.createChatGroup(this.chatGroupRepository);

        TeamChat teamChat = new TeamChat(teamId, chatGroupId);

        return this.teamChatRepository.save(teamChat);
    }

    private void removeTeam(Integer teamId) {
        Iterable<Member> members = this.memberRepository.findByTeamId(teamId);

        for (Member member : members) {
            this.memberRepository.delete(member);
        }
    }
}
package io.github.teamtracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.exception.TeamException;
import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.model.team.Team;
import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
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
        return this.teamRepository.save(team);
    }

    public void deleteTeam(Integer id) {
        this.teamRepository.deleteById(id);
    }

    public Member newTeam(Integer teamId, Integer userId) {
        this.teamRepository.findById(teamId).orElseThrow(() -> new TeamException("Team not found"));

        Member member = new Member(userId, teamId, "creator");

        return this.memberRepository.save(member);
    }

    public Member addUserToTeam(Integer teamId, Integer userId) {
        this.teamRepository.findById(teamId).orElseThrow(() -> new TeamException("Team not found"));

        if (this.memberRepository.findByUserIdAndTeamId(userId, teamId) != null) {
            throw new TeamException("User already in team");
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
}
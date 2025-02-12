package io.github.teamtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.exception.TeamException;
import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.model.team.Team;
import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.TeamRepository;

@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    public List<Team> getAllTeams() {
        return this.teamRepository.findAll();
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

    public Member addMemberToTeam(Integer teamId, Member member) {
        Team team = this.teamRepository.findById(teamId).orElseThrow(() -> new TeamException("Team not found"));

        team.addMember(member);

        return this.memberRepository.save(member);
    }

    public void removeMemberFromTeam(Integer teamId, Integer memberId) {
        Member member = this.memberRepository.findById(memberId)
                .orElseThrow(() -> new TeamException("Member not found"));

        Team team = this.teamRepository.findById(teamId).orElseThrow(() -> new TeamException("Team not found"));

        team.removeMember(member);

        this.memberRepository.delete(member);
    }
}
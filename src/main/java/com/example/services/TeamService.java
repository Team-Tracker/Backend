package com.example.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Member;
import com.example.models.Team;
import com.example.repositories.MemberRepository;
import com.example.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;

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
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public Member addMemberToTeam(Long teamId, Member member) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        team.addMember(member);
        return memberRepository.save(member);
    }

    public void removeMemberFromTeam(Long teamId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        team.removeMember(member);
        memberRepository.delete(member);
    }
}
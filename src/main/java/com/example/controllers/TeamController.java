package com.example.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.models.Member;
import com.example.models.Team;
import com.example.services.TeamService;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id).orElseThrow(() -> new IllegalArgumentException("Team not found"));
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

    @PostMapping("/{teamId}/members")
    public Member addMemberToTeam(@PathVariable Long teamId, @RequestBody Member member) {
        return teamService.addMemberToTeam(teamId, member);
    }

    @DeleteMapping("/{teamId}/members/{memberId}")
    public void removeMemberFromTeam(@PathVariable Long teamId, @PathVariable Long memberId) {
        teamService.removeMemberFromTeam(teamId, memberId);
    }
}
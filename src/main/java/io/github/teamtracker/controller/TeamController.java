package io.github.teamtracker.controller;

import org.springframework.web.bind.annotation.*;

import io.github.teamtracker.model.Member;
import io.github.teamtracker.model.Team;
import io.github.teamtracker.service.TeamService;

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
        return this.teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return this.teamService.getTeamById(id).orElseThrow(() -> new IllegalArgumentException("Team not found"));
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return this.teamService.createTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        this.teamService.deleteTeam(id);
    }

    @PostMapping("/{teamId}/members")
    public Member addMemberToTeam(@PathVariable Long teamId, @RequestBody Member member) {
        return this.teamService.addMemberToTeam(teamId, member);
    }

    @DeleteMapping("/{teamId}/members/{memberId}")
    public void removeMemberFromTeam(@PathVariable Long teamId, @PathVariable Long memberId) {
        this.teamService.removeMemberFromTeam(teamId, memberId);
    }
}
package io.github.teamtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.teamtracker.exception.TeamException;
import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.model.team.Team;
import io.github.teamtracker.service.TeamService;

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
    public Team getTeamById(@PathVariable Integer id) {
        return this.teamService.getTeamById(id).orElseThrow(() -> new TeamException("Team not found"));
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return this.teamService.createTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        this.teamService.deleteTeam(id);
    }

    @PostMapping("/{teamId}/members")
    public Member addMemberToTeam(@PathVariable Integer teamId, @PathVariable Member memberId) {
        return this.teamService.addMemberToTeam(teamId, memberId);
    }

    @DeleteMapping("/{teamId}/members/{memberId}")
    public void removeMemberFromTeam(@PathVariable Integer teamId, @PathVariable Integer memberId) {
        this.teamService.removeMemberFromTeam(teamId, memberId);
    }
}
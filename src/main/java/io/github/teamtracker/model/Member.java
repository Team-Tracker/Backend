package io.github.teamtracker.model;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(Integer userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Member(Integer userId, String role, Team team) {
        this.userId = userId;
        this.role = role;
        this.team = team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
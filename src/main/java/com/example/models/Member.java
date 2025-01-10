package com.example.models;

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

    public Member(Integer userId , String role, Team team) {
        this.userId = userId;
        this.role = role;
        this.team = team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
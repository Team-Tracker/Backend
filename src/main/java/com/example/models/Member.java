package com.example.models;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String role; // e.g., "Admin", "User", etc.

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Member(String name, String role, Team team) {
        this.name = name;
        this.role = role;
        this.team = team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
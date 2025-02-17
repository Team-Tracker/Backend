package io.github.teamtracker.model.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
// Combination of user_id and team_id must be unique
@Table(name = "member", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "team_id" }))
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "team_id")
    private Integer teamId;

    private String role;

    public Member() {

    }

    public Member(Integer userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public Member(Integer userId, Integer teamId, String role) {
        this.userId = userId;
        this.teamId = teamId;
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return this.teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
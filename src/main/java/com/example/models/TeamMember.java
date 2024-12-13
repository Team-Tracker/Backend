package com.example.models;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class TeamMember implements Serializable {

    private Integer team_id;

    private Integer user_id;

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
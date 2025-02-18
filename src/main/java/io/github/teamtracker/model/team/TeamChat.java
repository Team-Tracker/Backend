package io.github.teamtracker.model.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TeamChat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "chat_group_id")
    private Integer chatGroupId;

    @Column(name = "team_id")
    private Integer teamId;

    public TeamChat() {

    }

    public TeamChat(Integer chatGroupId, Integer teamId) {
        this.chatGroupId = chatGroupId;
        this.teamId = teamId;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getChatGroupId() {
        return this.chatGroupId;
    }

    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    public Integer getTeamId() {
        return this.teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
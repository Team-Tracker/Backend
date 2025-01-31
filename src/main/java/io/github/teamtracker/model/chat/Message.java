package io.github.teamtracker.model.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userid;

    @Column(name = "chat_group_id")
    private Integer chatGroupId;

    private String text;

    private final String timestamp;

    public Message() {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userId) {
        this.userid = userId;
    }

    public Integer getChatGroupId() {
        return this.chatGroupId;
    }

    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return this.timestamp;
    }
}
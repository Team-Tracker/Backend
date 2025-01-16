package io.github.teamtracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // @Column(name = "name")
    private String name;

    // @Column(name = "timestamp")
    private final String timestamp;

    public ChatGroup() {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    /*
     * public void setTimestamp(String timestamp) {
     * this.timestamp = timestamp;
     * }
     */
}
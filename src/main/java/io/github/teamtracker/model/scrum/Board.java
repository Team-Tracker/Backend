package io.github.teamtracker.model.scrum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "sprint_id")
    private Integer sprintId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSprintId() {
        return this.sprintId;
    }

    public void setSprintId(Integer sprintId) {
        this.sprintId = sprintId;
    }
}
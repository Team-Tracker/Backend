package io.github.teamtracker.model.team;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "creater_id")
    private Integer createrId;

    private String description;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Member> members = new HashSet<>();

    public void addMember(Member member) {
        this.members.add(member);

        member.setTeam(this);
    }

    public void removeMember(Member member) {
        this.members.remove(member);

        member.setTeam(null);
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreaterId() {
        return this.createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Member> getMembers() {
        return this.members;
    }
}
package com.solid.solidbackend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaderId", nullable = false)
    private User leader;

    @Transient
    private List<User> teamMembers;

    @Transient
    private float teamGrade;


    public Team() {

    }

    public Team(String name, User leader) {
        this.name = name;
        this.leader = leader;
    }


}

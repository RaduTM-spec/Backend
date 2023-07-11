package com.solid.solidbackend.Entities;

import com.solid.solidbackend.Entities.User;
import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private User leader;

    // Constructors, getters, and setters


    public Team() {
    }

    public Team(String name, User leader) {
        this.name = name;
        this.leader = leader;
    }

    public Team(Long id, String name, User leader) {
        this.id = id;
        this.name = name;
        this.leader = leader;
    }
}

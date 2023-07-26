package com.solid.solidbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leaderId", nullable = false, referencedColumnName = "id")
    private User leader;

    public Team() {

    }

    public Team(Long id, String name, User leader) {
        this.id = id;
        this.name = name;
        this.leader = leader;
    }

    public Team(String name, User leader) {
        this.name = name;
        this.leader = leader;
    }


}

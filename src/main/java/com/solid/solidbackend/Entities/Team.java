package com.solid.solidbackend.Entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.LinkedList;

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
}

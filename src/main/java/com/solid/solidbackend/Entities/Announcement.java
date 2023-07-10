package com.solid.solidbackend.Entities;

import jakarta.persistence.*;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

    // Constructors, getters, and setters
}


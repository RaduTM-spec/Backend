package com.solid.solidbackend.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("meeting")
public class Meeting extends Message {
    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    // Constructors, getters, and setters
}

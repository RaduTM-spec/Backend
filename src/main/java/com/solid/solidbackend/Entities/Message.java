package com.solid.solidbackend.Entities;

import com.solid.solidbackend.Entities.Enums.MessageType;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "message_type")
public abstract class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType type;

    @Column(nullable = false)
    private String title;

    @Column
    private String details;

    // Constructors, getters, and setters
}

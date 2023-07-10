package com.solid.solidbackend.Entities;

import com.solid.solidbackend.Entities.Enums.Role;
import com.solid.solidbackend.Entities.Enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column
    private String pictureUrl;

    // Constructors, getters, and setters
}


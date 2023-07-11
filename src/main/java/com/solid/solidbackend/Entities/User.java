package com.solid.solidbackend.Entities;

import com.solid.solidbackend.Enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String pictureUrl;

    // Constructors (one empty, one complete, one wo id)

    public User() {
    }

    public User(String name, Role role, String pictureUrl) {
        this.name = name;
        this.role = role;
        this.pictureUrl = pictureUrl;
    }

    public User(Long id, String name, Role role, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.pictureUrl = pictureUrl;
    }
}


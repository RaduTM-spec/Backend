package com.solid.solidbackend.entities;

import com.solid.solidbackend.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String pictureUrl;

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


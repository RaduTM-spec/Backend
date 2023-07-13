package com.solid.solidbackend.Entities;

import com.solid.solidbackend.Enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false)
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
}


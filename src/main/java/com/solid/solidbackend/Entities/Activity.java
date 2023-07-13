package com.solid.solidbackend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "activities")
public class Activity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creatorId", nullable = false)
    private User creator;

    @Column
    private String deadline;

    public Activity() {
    }

    public Activity(String name, User creator, String deadline) {
        this.name = name;
        this.creator = creator;
        this.deadline = deadline;
    }
}

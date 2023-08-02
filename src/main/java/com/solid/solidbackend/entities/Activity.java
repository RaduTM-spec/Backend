package com.solid.solidbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "creatorId", referencedColumnName = "id")
    private User creator;

    private String deadline;


    public Activity() {
    }

    public Activity(String name, User creator, String deadline) {
        this.name = name;
        this.creator = creator;
        this.deadline = deadline;
    }


    public Activity(Long id, String name, User creator, String deadline) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.deadline = deadline;
    }

}

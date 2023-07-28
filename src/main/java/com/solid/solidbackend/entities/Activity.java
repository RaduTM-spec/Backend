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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creatorId", referencedColumnName = "id")
    private User creator;

    private String deadline;
//
//    @ManyToMany(mappedBy = "activities")
//    private Set<Team> teams = new HashSet<>();
//
//    @ManyToMany(mappedBy = "activities")
//    private Set<User> mentors = new HashSet<>();


    public Activity() {
    }

    public Activity(String name, User creator, String deadline) {
        this.name = name;
        this.creator = creator;
        this.deadline = deadline;
    }

//    public Activity(String name, User creator, String deadline, Set<Team> teams, Set<User> mentors) {
//        this.name = name;
//        this.creator = creator;
//        this.deadline = deadline;
//        this.teams = teams;
//        this.mentors = mentors;
//    }

    public Activity(Long id, String name, User creator, String deadline) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.deadline = deadline;
    }

//    public Activity(Long id, String name, User creator, String deadline, Set<Team> teams, Set<User> mentors) {
//        this.id = id;
//        this.name = name;
//        this.creator = creator;
//        this.deadline = deadline;
//        this.teams = teams;
//        this.mentors = mentors;
//    }
}

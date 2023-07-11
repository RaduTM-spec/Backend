package com.solid.solidbackend.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "grade_comments")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private User mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private User member;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime edited;

    @Column(nullable = false)
    private Float grade;

    // Constructors, getters, and setters

    public Assessment() {
    }

    public Assessment(User mentor, User member, String comment, LocalDateTime edited, Float grade) {
        this.mentor = mentor;
        this.member = member;
        this.comment = comment;
        this.edited = edited;
        this.grade = grade;
    }

    public Assessment(Long id, User mentor, User member, String comment, LocalDateTime edited, Float grade) {
        this.id = id;
        this.mentor = mentor;
        this.member = member;
        this.comment = comment;
        this.edited = edited;
        this.grade = grade;
    }
}

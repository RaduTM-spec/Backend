package com.solid.solidbackend.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "grade_comments")
public class GradeComment {
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
}

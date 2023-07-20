package com.solid.solidbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "assessments")
public class Assessment extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorId", nullable = false)
    private User mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private User member;

    @Column(nullable = false)
    private Float grade;

    @Column(nullable = false)
    private Boolean attended;

    @Column
    private String comment;

    public Assessment() {
    }

    public Assessment(String title, Activity activity, User mentor, User member,
                      Float grade, Boolean attended, String comment) {
        this.title = title;
        this.activity = activity;
        this.mentor = mentor;
        this.member = member;
        this.grade = grade;
        this.attended = attended;
        this.comment = comment;
    }
}

package com.solid.solidbackend.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "assessments")
@JsonPropertyOrder({ "id", "title", "activity", "mentor", "user", "grade", "attended", "comment" })
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activityId", referencedColumnName = "id")
    private Activity activity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentorId", referencedColumnName = "id")
    private User mentor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private Float grade = 0.0f;

    @Column(nullable = false)
    private boolean attended = false;

    @Column(nullable = false)
    private String comment = "None";

    public Assessment() {
    }

    public Assessment(Long id, String title, Activity activity, User mentor, User user, float grade, boolean attended, String comment) {
        this.id = id;
        this.title = title;
        this.activity = activity;
        this.mentor = mentor;
        this.user = user;
        this.grade = grade;
        this.attended = attended;
        this.comment = comment;
    }

    public Assessment(String title, Activity activity, User mentor, User user, float grade, boolean attended, String comment) {
        this.title = title;
        this.activity = activity;
        this.mentor = mentor;
        this.user = user;
        this.grade = grade;
        this.attended = attended;
        this.comment = comment;
    }
}

package com.solid.solidbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mentorsActivities")
public class MentorActivity extends BaseEntity {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentorId", nullable = false)
    private List<User> mentors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    public MentorActivity() {
    }

    public MentorActivity(List<User> mentors, Activity activity) {
        this.mentors = mentors;
        this.activity = activity;
    }
}

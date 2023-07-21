package com.solid.solidbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mentorsActivities")
public class MentorActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentorId", referencedColumnName = "id")
    private User mentor;

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "id")
    private Activity activity;


    public MentorActivity() {
    }

    public MentorActivity(User mentor, Activity activity) {
        this.mentor = mentor;
        this.activity = activity;
    }

    public MentorActivity(Long id, User mentor, Activity activity) {
        this.id = id;
        this.mentor = mentor;
        this.activity = activity;
    }
}

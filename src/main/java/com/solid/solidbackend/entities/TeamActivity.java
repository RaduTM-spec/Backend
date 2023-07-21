package com.solid.solidbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teamsActivities")
public class TeamActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "activityId", referencedColumnName = "id")
    private Activity activity;

    public TeamActivity() {
    }

    public TeamActivity(Team team, Activity activity) {
        this.team = team;
        this.activity = activity;
    }

    public TeamActivity(Long id, Team team, Activity activity) {
        this.id = id;
        this.team = team;
        this.activity = activity;
    }
}

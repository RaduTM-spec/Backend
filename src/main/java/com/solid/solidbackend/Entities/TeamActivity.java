package com.solid.solidbackend.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teamsActivities")
public class TeamActivity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", nullable = false)
    private Activity activity;

    public TeamActivity() {
    }

    public TeamActivity(Team team, Activity activity) {
        this.team = team;
        this.activity = activity;
    }
}

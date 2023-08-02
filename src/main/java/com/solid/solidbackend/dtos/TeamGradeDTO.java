package com.solid.solidbackend.dtos;

import com.solid.solidbackend.entities.Team;

/**
 * Data Transfer Object representing a team and its computed grade in an activity.
 */
public class TeamGradeDTO {
    public Team team;
    public Float grade;

    public TeamGradeDTO(Team team, Float grade) {
        this.team = team;
        this.grade = grade;
    }
}

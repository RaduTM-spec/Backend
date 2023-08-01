package com.solid.solidbackend.dtos;

import com.solid.solidbackend.entities.Team;

public class TeamGradeDTO {
    public Team team;
    public Float grade;

    public TeamGradeDTO(Team team, Float grade) {
        this.team = team;
        this.grade = grade;
    }
}

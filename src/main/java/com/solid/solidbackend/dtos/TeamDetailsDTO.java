package com.solid.solidbackend.dtos;

import com.solid.solidbackend.entities.User;

import java.util.List;

/**
 * Data Transfer Object representing the details of an entire team's performance in an activity, including members.
 */
public class TeamDetailsDTO {
    public List<User> members;
    public List<Float> gradesPerMember;
    public List<Integer> attendancesPerMember;
    public Float teamGrade;

    public TeamDetailsDTO(List<User> members, List<Float> gradesPerMember, List<Integer> attendancesPerMember, Float teamGrad) {
        this.members = members;
        this.gradesPerMember = gradesPerMember;
        this.attendancesPerMember = attendancesPerMember;
        this.teamGrade = teamGrad;
    }
}

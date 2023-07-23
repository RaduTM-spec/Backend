package com.solid.solidbackend.entities;

import java.util.List;

public class TeamDetails {
    public List<User> members;
    public List<Float> gradesPerMember;
    public List<Integer> attendancesPerMember;
    public Float teamGrade;

    public TeamDetails(List<User> members, List<Float> gradesPerMember, List<Integer> attendancesPerMember, Float teamGrade) {
        this.members = members;
        this.gradesPerMember = gradesPerMember;
        this.attendancesPerMember = attendancesPerMember;
        this.teamGrade = teamGrade;
    }
}

package com.solid.solidbackend.Services;

import com.solid.solidbackend.Entities.Assessment;
import com.solid.solidbackend.Entities.Team;
import com.solid.solidbackend.Repositories.TeamRepository;
import com.solid.solidbackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getTeamByName(String name)
    {
        // get the team
        // initialize transient fields
        Team team = null;

        // Setup teamMembers


        // Setup teamGrade
        // List<Assessment> assessments = assessmentRepository.findAllByUserId()
        // team.teamMembers.forEach(x -> assessments.addAll(x.getAllAssessments()));
        // team.teamGrade = 0f;
        // for (var ass :
        //         assessments) {
        //     team.teamGrade += ass.getGrade();
        // }
        // team.teamGrade /= assessments.size();

        return team;
    }
}

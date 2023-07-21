package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.repositories.apprepository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

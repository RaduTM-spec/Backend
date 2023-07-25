package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamActivity;
import com.solid.solidbackend.repositories.apprepository.TeamActivityRepository;
import com.solid.solidbackend.services.TeamActivityService;
import org.springframework.stereotype.Service;

@Service
public class TeamActivityServiceImpl implements TeamActivityService {

    private final TeamActivityRepository teamActivityRepository;

    public TeamActivityServiceImpl(TeamActivityRepository teamActivityRepository) {
        this.teamActivityRepository = teamActivityRepository;
    }

    @Override
    public TeamActivity joinActivityByTeam(Activity joinedActivity, Team joiningTeam) {

        TeamActivity activityMembership = new TeamActivity();
        activityMembership.setActivity(joinedActivity);
        activityMembership.setTeam(joiningTeam);

        return teamActivityRepository.save(activityMembership);
    }

}

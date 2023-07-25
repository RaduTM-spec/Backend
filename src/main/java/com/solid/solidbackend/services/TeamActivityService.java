package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamActivity;

public interface TeamActivityService {
    TeamActivity joinActivityByTeam(Activity joinedActivity, Team joiningTeam);
}

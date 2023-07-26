package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamDetails;
import com.solid.solidbackend.entities.User;

import java.util.List;

public interface TeamService {

    Team getTeamByName(String name);

    User addUserToTeam(String username, String teamName);

    TeamDetails getTeamDetailsFromAnActivity(String activityName, String teamName);

    Team createTeam(String teamName, User teamLeader);

    List<Team> getTeamsByActivity(String activityName);
}

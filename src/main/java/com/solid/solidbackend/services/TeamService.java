package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamDetails;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.exceptions.TeamExistsException;

import java.util.List;

public interface TeamService {

    Team getTeamByName(String name);

    List<Team> getTeamsByActivity(String username, String activityName);

    User addUserToTeam(String username, String teamName);

    TeamDetails getTeamDetailsFromAnActivity(String activityName, String teamName);

    Team createTeam(String teamName, User teamLeader) throws TeamExistsException;

}

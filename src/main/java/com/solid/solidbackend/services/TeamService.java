package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamDetails;
import com.solid.solidbackend.entities.User;

import java.util.List;

public interface TeamService {

    public Team getTeamByName(String name);
    public User addUserToTeam(String username, String teamName);
    public TeamDetails getTeamDetailsFromAnActivity(String activityName, String teamname);
    public Team createTeam(String teamName, User teamLeader);
    List<Team> getTeamsByActivity(String activityName);
}

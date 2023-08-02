package com.solid.solidbackend.dtos;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.User;

/**
 * Data Transfer Object representing a User and his associated Team.
 */
public class UserTeamDTO {
    public User user;
    public Team team;

    public UserTeamDTO(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    public UserTeamDTO(User user) {
        this.user = user;
    }
}



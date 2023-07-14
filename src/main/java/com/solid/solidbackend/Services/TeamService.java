package com.solid.solidbackend.Services;

import com.solid.solidbackend.Entities.Team;
import com.solid.solidbackend.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository
    ) {
        this.teamRepository = teamRepository;
    }

    public Team getTeam(Long id) {
        if (teamRepository.findById(id).isPresent())
            return teamRepository.findById(id).get();
        return null;
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
}
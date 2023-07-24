package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamActivityRepository extends JpaRepository<TeamActivity, Long> {

    @Query("SELECT ta FROM TeamActivity ta WHERE ta.activity.id = :activityId")
    List<Team> findAllTeamsByActivityId(Long activityId);

    @Query("SELECT ta FROM TeamActivity ta WHERE ta.activity.name = :activityName")
    List<TeamActivity> findAllTeamsByActivityName(String activityName);

    @Query("SELECT ta FROM TeamActivity ta WHERE ta.team.id = :teamId")
    List<TeamActivity> findAllActivitiesByTeamId(Long teamId);
}

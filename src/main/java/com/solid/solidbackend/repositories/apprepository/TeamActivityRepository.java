package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.TeamActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamActivityRepository extends JpaRepository<TeamActivity, Long> {

    @Query("SELECT ta FROM TeamActivity ta WHERE ta.team.id = :teamId")
    List<TeamActivity> findAllActivitiesByTeamId(Long teamId);

    @Query("SELECT ta FROM TeamActivity ta WHERE ta.activity.name = :activityName")
    List<TeamActivity> findAllByActivityName(@Param("activityName") String activityName);

}

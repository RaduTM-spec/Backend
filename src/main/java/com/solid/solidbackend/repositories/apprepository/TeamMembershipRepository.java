package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamMembership;
import com.solid.solidbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamMembershipRepository extends JpaRepository <TeamMembership, Long> {

    @Query("SELECT tm FROM TeamMembership tm WHERE tm.team.name = :teamName")
    List<TeamMembership> findAllTeamMembershipsByTeamName(String teamName);

    @Query("SELECT tm.team FROM TeamMembership tm WHERE tm.user.id = :userId")
    Optional<Team> findTeamByUserId(Long userId);

    TeamMembership findByUserId(Long userId);

    @Query("SELECT tm FROM TeamMembership tm WHERE tm.team.id = :teamId  AND tm.user.name = :username  " )
    Optional<TeamMembership> findByTeamAndUser(Long teamId, String username);
}

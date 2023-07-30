package com.solid.solidbackend.services;

import com.solid.solidbackend.dtos.UserTeamDTO;
import com.solid.solidbackend.entities.User;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {

    @Transactional
    UserTeamDTO createLeader_addLeaderToTeam(String username, String teamName);

    @Transactional
    UserTeamDTO createMember_addMemberToTeam(String username, String teamName);

    @Transactional
    UserTeamDTO createMentor_addMentorToActivity(String activityName, String userName, String dueDate);

    @Transactional
    UserTeamDTO createMentor_createActivity_addMentorToActivity(String activityName, String userName, String dueDate);


    UserTeamDTO authenticateUser(String name);
}



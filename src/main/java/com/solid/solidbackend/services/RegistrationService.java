package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.User;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {

    User authenticateExistingUser(User user);

    @Transactional
    User createLeader_addLeaderToTeam(String username, String teamName);

    @Transactional
    User createMember_addMemberToTeam(String username, String teamName);

    @Transactional
    User createMentor_addMentorToActivity(String activityName, String userName, String dueDate);

    @Transactional
    User createMentor_createActivity_addMentorToActivity(String activityName, String userName, String dueDate);

    User registerNewMember(User user, String activityName);

    User registerNewMentor(User user, String activityName, String dueDate);

    User registerNewLead(User user, String teamName);
}



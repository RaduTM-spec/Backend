package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RegistrationService {

    User authenticateExistingUser(User user);

    @Transactional
    User createAndAddLeadToTeam(String username, String teamName);

    @Transactional
    User createAndAddMemberToTeam(String username, String teamName);

    @Transactional
    User createAndAddMentorToExistingActivity(String activityName, String userName, String dueDate);

    @Transactional
    User createAndAddMentorToNewActivity(String activityName, String userName, String dueDate);

    User registerNewMember(User user, String activityName);

    User registerNewMentor(User user, String activityName, String dueDate);

    User registerNewLead(User user, String teamName);
}



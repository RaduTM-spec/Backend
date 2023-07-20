package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.User;

import java.util.Optional;

public interface RegistrationService {

    User authenticateExistingUser(User user);

    User registerNewMember(User user, String activityName);

    User registerNewMentor(User user, String activityName, String dueDate);

    User registerNewLead(User user, String teamName);
}



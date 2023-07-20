package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.services.RegistrationService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User authenticateExistingUser(User user) {
        // more logic needed here
        return userService.saveUser(user);
    }

    @Override
    public User registerNewMember(User user, String activityName) {
        // more logic needed here
        return userService.saveUser(user);
    }

    @Override
    public User registerNewMentor(User user, String activityName, String dueDate) {
        // more logic needed here

        return userService.saveUser(user);

    }

    @Override
    public User registerNewLead(User user, String teamName) {
        // more logic needed here
        return userService.saveUser(user);
    }
}



package com.solid.solidbackend.services.implementations;


import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.UserCreationException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
import com.solid.solidbackend.repositories.apprepository.*;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;
    private final TeamActivityRepository teamActivityRepository;
    private final TeamMembershipRepository teamMembershipRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AssessmentRepository assessmentRepository,
                           TeamActivityRepository teamActivityRepository, TeamMembershipRepository teamMembershipRepository,
                           ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
        this.teamActivityRepository = teamActivityRepository;
        this.teamMembershipRepository = teamMembershipRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("User not found with name: " + name));
    }

    @Override
    public User getUserByNameAndRole(String name, String role) {
        return null;
    }

    @Override
    public User saveUser(User user) {

        // we check if the user already exists by name
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserCreationException("Username already exists: " + user.getName());
        }

        return userRepository.save(user);
    }

    @Override
    public User createNewUser(String name, Role role) {

        String pictureUrl = "https://robohash.org/"+ name + ".png";

        User newUser = new User(name, role, pictureUrl);

        return saveUser(newUser);
    }








}
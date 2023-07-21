package com.solid.solidbackend.services.implementations;


import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.exceptions.UserCreationException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
import com.solid.solidbackend.repositories.apprepository.AssessmentRepository;
import com.solid.solidbackend.repositories.apprepository.UserRepository;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AssessmentRepository assessmentRepository) {
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("User not found with name: " + name));
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
    public List<Activity> getUserActivities(Long userId) {
        return null;
    }

    @Override
    public List<Assessment> getUserAssessments(Long userId) {
        return null;
    }


}
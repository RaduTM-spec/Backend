package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.entities.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserByName(String name);
    User saveUser(User user);
    List<Activity> getUserActivities(Long userId);

    List<Assessment> getUserAssessments(Long userId);
}

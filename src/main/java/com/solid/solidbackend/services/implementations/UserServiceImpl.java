package com.solid.solidbackend.services.implementations;


import com.solid.solidbackend.entities.*;
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
    public UserServiceImpl(UserRepository userRepository, AssessmentRepository assessmentRepository, TeamActivityRepository teamActivityRepository, TeamMembershipRepository teamMembershipRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
        this.teamActivityRepository = teamActivityRepository;
        this.teamMembershipRepository = teamMembershipRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("User not found with name: " + name));
    }

    @Override
    public User saveUser(User user) {

        // if user exists, is GET
        // else, is POST

        // we check if the user already exists by name

        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserCreationException("Username already exists: " + user.getName());
        }

        return userRepository.save(user);
    }

    @Override
    public List<Activity> getUserActivities(Long userId) {
        // Get user's team
        Team team = teamMembershipRepository.findTeamByUserId(userId);

        // Use team's id to retrieve all activities
        List<TeamActivity> teamActivities = teamActivityRepository.findAllActivitiesByTeamId(team.getId());
        return teamActivities.stream().map(TeamActivity::getActivity).toList();
    }

    @Override
    public List<Assessment> getUserAssessments(Long userId) {
        return null;
    }

    @Override
    public Activity createActivity(Activity activity) {
        // You might want to move this into it's own service idk
        activityRepository.save(activity);
        return activity;
    }
}
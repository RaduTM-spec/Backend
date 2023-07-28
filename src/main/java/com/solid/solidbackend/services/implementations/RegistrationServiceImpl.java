package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.ActivityAlreadyExistsException;
import com.solid.solidbackend.exceptions.NoActivityFoundException;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;
    private final MentorActivityService mentorActivityService;
    private final TeamService teamService;
    private final ActivityRepository activityRepository;

    @Autowired
    public RegistrationServiceImpl(UserService userService, MentorActivityService mentorActivityService, ActivityService activityService, TeamService teamService, ActivityRepository activityRepository) {
        this.userService = userService;
        this.mentorActivityService = mentorActivityService;
        this.teamService = teamService;
        this.activityRepository = activityRepository;
    }

    @Override
    public User authenticateExistingUser(User user) {
        // more logic needed here

        return userService.saveUser(user);
    }

    @Override
    @Transactional
    public User createAndAddLeadToTeam(String username, String teamName) {
        User newLead = userService.createNewUser(username, Role.TEAM_LEADER);
        teamService.createTeam(teamName, newLead);
        return teamService.addUserToTeam(username, teamName);
    }

    @Override
    @Transactional
    public User createAndAddMemberToTeam(String username, String teamName) {
        User newMember = userService.createNewUser(username, Role.MEMBER);
        return teamService.addUserToTeam(newMember.getName(), teamName);
    }


    @Override
    @Transactional
    public User createAndAddMentorToExistingActivity(String activityName, String userName, String dueDate) {
        userService.createNewUser(userName, Role.MENTOR);

        Activity existingActivity = activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException(activityName)
        );

        User newMentor = userService.createNewUser(userName, Role.MENTOR);

        mentorActivityService.linkMentorWithActivity(newMentor, existingActivity);

        return newMentor;
    }

    @Override
    @Transactional
    public User createAndAddMentorToNewActivity(String activityName, String userName, String dueDate) {
        User newMentor = userService.createNewUser(userName, Role.MENTOR);

        if (activityRepository.findActivityByName(activityName).isPresent()) {
            throw new ActivityAlreadyExistsException(activityName);
        }

        Activity newActivity = new Activity();
        newActivity.setName(activityName);
        newActivity.setCreator(newMentor);
        newActivity.setDeadline(dueDate);

        activityRepository.save(newActivity);

        return newMentor;
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



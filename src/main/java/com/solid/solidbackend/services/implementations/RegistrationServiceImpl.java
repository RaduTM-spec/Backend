package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.*;
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
    public User createLeader_addLeaderToTeam(String username, String teamName) {
        User newLead = userService.createUser(username, Role.TEAM_LEADER);
        teamService.createTeam(teamName, newLead);
        return teamService.addUserToTeam(username, teamName);
    }

    @Override
    @Transactional
    public User createMember_addMemberToTeam(String username, String teamName) {
        User newMember = userService.createUser(username, Role.MEMBER);
        return teamService.addUserToTeam(newMember.getName(), teamName);
    }


    @Override
    @Transactional
    public User createMentor_addMentorToActivity(String activityName, String userName, String dueDate) throws UserExistsException, ActivityNotFoundException{
        User newMentor = userService.createUser(userName, Role.MENTOR);


        Activity existingActivity = activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new ActivityNotFoundException(activityName)
        );


        mentorActivityService.linkMentorWithActivity(newMentor, existingActivity);

        return newMentor;
    }

    @Override
    @Transactional
    public User createMentor_createActivity_addMentorToActivity(String activityName, String userName, String dueDate) {
        User newMentor = userService.createUser(userName, Role.MENTOR);

        if (activityRepository.findActivityByName(activityName).isPresent()) {
            throw new ActivityAlreadyExistsException(activityName);
        }

        Activity newActivity = new Activity();
        newActivity.setName(activityName);
        newActivity.setCreator(newMentor);
        newActivity.setDeadline(dueDate);

        activityRepository.save(newActivity);
        mentorActivityService.linkMentorWithActivity(newMentor, newActivity);

        return newMentor;
    }



    // Astea de mai jos sa ma bata tata de stiu ce rost au. ~semnat Radu
    @Override
    public User registerNewMember(User user, String activityName) {
        // more logic needed here

        return userService.saveUser(user);
    }

    @Override
    public User registerNewMentor(User user, String activityName, String dueDate) throws UserExistsException {
        // more logic needed here

        return userService.saveUser(user);
    }

    @Override
    public User registerNewLead(User user, String teamName) {
        // more logic needed here

        return userService.saveUser(user);
    }
}



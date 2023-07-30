package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.dtos.UserTeamDTO;
import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Team;
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
    @Transactional
    public UserTeamDTO createLeader_addLeaderToTeam(String username, String teamName) {
        User newLead = userService.createUser(username, Role.TEAM_LEADER);
        Team createdTeam = teamService.createTeam(teamName, newLead);
        teamService.addUserToTeam(username, teamName);
        return new UserTeamDTO(newLead, createdTeam);
    }

    @Override
    @Transactional
    public UserTeamDTO createMember_addMemberToTeam(String username, String teamName) {
        User newMember = userService.createUser(username, Role.MEMBER);
        teamService.addUserToTeam(newMember.getName(), teamName);
        Team joinedTeam = teamService.getTeamByName(teamName);

        return new UserTeamDTO(newMember, joinedTeam);
    }


    @Override
    @Transactional
    public UserTeamDTO createMentor_addMentorToActivity(String activityName, String userName, String dueDate) throws UserExistsException, ActivityNotFoundException{
        User newMentor = userService.createUser(userName, Role.MENTOR);


        Activity existingActivity = activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new ActivityNotFoundException(activityName)
        );


        mentorActivityService.linkMentorWithActivity(newMentor, existingActivity);

        return new UserTeamDTO(newMentor);
    }

    @Override
    @Transactional
    public UserTeamDTO createMentor_createActivity_addMentorToActivity(String activityName, String userName, String dueDate) {
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

        return new UserTeamDTO(newMentor);
    }

    @Override
    public UserTeamDTO authenticateUser(String name) {

        User authenticatedUser = userService.findUserByName(name);

        if (authenticatedUser.getRole() == Role.MENTOR) {
            return new UserTeamDTO(authenticatedUser);
        } else {
            Team team = teamService.getTeamByUserId(authenticatedUser.getId());
            return new UserTeamDTO(authenticatedUser, team);
        }

    }

}



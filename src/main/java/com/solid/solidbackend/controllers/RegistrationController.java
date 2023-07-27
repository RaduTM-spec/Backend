package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.MentorActivityService;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.implementations.TeamServiceImpl;
import com.solid.solidbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")

public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    // here you inject the service beans that you use
    private final UserService userService;
    private final ActivityService activityService;


    public RegistrationController(UserService userService, ActivityService activityService1) {
        this.userService = userService;
        this.activityService = activityService1;

    }

    @Operation(summary = "authenticate existing user")
    @PostMapping("/")
    public ResponseEntity<User> authenticateUser(String name) {

        logger.info(" > Authenticating existing user: {}", name);
        User x = userService.getUserByName(name);
        logger.info(" > User authenticated successfully!");
        return ResponseEntity.ok(x);
    }

    @Operation(summary = "creates new member and enrolls him in an existing team")
    @PostMapping("/new/member/{username}/{teamName}")
    public ResponseEntity<User> createMemberAndAddMemberToTeam(@PathVariable String username,
                                                               @PathVariable String teamName) {

        logger.info(" > Creating a new member with username: {} and adding to team: {}", username, teamName);
        User addedMember = userService.createAndAddMemberToTeam(username, teamName);
        return ResponseEntity.ok(addedMember);
    }

    /**
     *
     * 1. Creates a new mentor user
     * 2. If the activity entered exists, it links the mentor with the activity
     *    Else it creates a new activity and links the mentor to it;
     * @param userName
     * @param activityName
     * @param dueDate
     * @return
     */
    @Operation(summary = "creates new mentor and enrolls him in new or existing activity")
    @PostMapping("/new/mentor/{userName}/{activityName}/{dueDate}")
    public ResponseEntity<User> createMentorAndAddMentor(@PathVariable  String userName,
                                                        @PathVariable String activityName,
                                                        @PathVariable String dueDate) {

        logger.info(" > Creating a new mentor with username: {}", userName);
        User newMentor = userService.createNewUser(userName, Role.MENTOR);
        // to handle the new mentor linking to existing/new activity
        this.activityService.addNewMentorToActivity(activityName, newMentor, dueDate);
        return ResponseEntity.ok(newMentor);
    }


    /**
     * 1. Creates a new leader
     * 2. If the team already exists, its bad
     *    Else it creates a team and a membership it;
     * @param userName
     * @param teamName
     * @return
     */
    @Operation(summary = "creates new lead and creates a new team for him")
    @PostMapping("/new/lead/{userName}/{teamName}")
    public ResponseEntity<User> createLeadAndAddLead(@PathVariable  String userName,
                                                     @PathVariable String teamName) {

        logger.info(" > Creating a new lead with username: {} and adding to team: {}", userName, teamName);
        User user = userService.createAndAddLeadToTeam(userName, teamName);
        return ResponseEntity.ok(user);
    }


}
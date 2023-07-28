package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.services.RegistrationService;
import com.solid.solidbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user registration and authentication operations.
 */

@Slf4j
@RestController
@RequestMapping("/")
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    /**
     * Authenticates an existing user.
     *
     * @param name The name of the user to be authenticated.
     * @return A ResponseEntity containing the User object representing the authenticated user.
     */
    @PostMapping("/authenticate")
    @Operation(summary = "authenticate existing user")
    public ResponseEntity<User> authenticateUser(@RequestParam String name) {

        log.info(" > Authenticating existing user: {}", name);
        User authenticatedUser = userService.findUserByName(name);
        log.info(" > User authenticated successfully!");
        return ResponseEntity.ok(authenticatedUser);
    }


    /**
     * Creates a new MEMBER and enrolls him in an existing team.
     *
     * @param username The username of the new member to be created.
     * @param teamName The name of the team in which the new member will be enrolled.
     * @return A ResponseEntity containing the User object representing the newly created member.
     */
    @PostMapping("/new/member")
    @Operation(summary = "creates new member and enrolls him in an existing team")
    public ResponseEntity<User> createMemberAndAddMemberToTeam(@RequestParam String username,
                                                               @RequestParam String teamName) {

        log.info(" > Creating a new member with username: {} and adding to team: {}", username, teamName);
        User addedMember = registrationService.createMember_addMemberToTeam(username, teamName);
        return ResponseEntity.ok(addedMember);
    }


    /**
     * Creates a new MENTOR and enrolls them in a new or existing activity.
     *
     * @param userName     The username of the new mentor to be created.
     * @param create       Flag to check if the user wants to create a new activity or join existing one
     * @param activityName The name of the activity to be linked with the mentor.
     * @param dueDate      The due date for the mentor's activity.
     * @return A ResponseEntity containing the User object representing the newly created mentor.
     */
    @PostMapping("/new/mentor")
    @Operation(summary = "creates new mentor and enrolls him in new or existing activity")
    public ResponseEntity<User> createMentorAndAddMentor(@RequestParam String userName,
                                                         @RequestParam Boolean create,
                                                         @RequestParam String activityName,
                                                         @RequestParam String dueDate) {

        User newMentor;
        log.info(" > Creating a new mentor with username: {}", userName);

        if (create) {
            newMentor = registrationService.createMentor_createActivity_addMentorToActivity(activityName, userName, dueDate);
        } else {
            newMentor = registrationService.createMentor_addMentorToActivity(activityName, userName, dueDate);
        }

        return ResponseEntity.ok(newMentor);
    }


    /**
     * Creates a new LEAD and a new team for them.
     *
     * @param userName The username of the new lead to be created.
     * @param teamName The name of the new team to be created and associated with the lead.
     * @return A ResponseEntity containing the User object representing the newly created lead.
     */
    @PostMapping("/new/lead")
    @Operation(summary = "creates new lead and creates a new team for him")
    public ResponseEntity<User> createLeadAndAddLead(@RequestParam String userName,
                                                     @RequestParam String teamName) {

        log.info(" > Creating a new leader with username: {} and adding to team: {}", userName, teamName);
        User user = registrationService.createLeader_addLeaderToTeam(userName, teamName);
        return ResponseEntity.ok(user);
    }
}

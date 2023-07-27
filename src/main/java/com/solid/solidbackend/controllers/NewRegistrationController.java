package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user registration and authentication operations.
 */

@Slf4j
@RestController
@RequestMapping("/")
public class NewRegistrationController {

    private final UserService userService;
    private final ActivityService activityService;

    public NewRegistrationController(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
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
        User authenticatedUser = userService.getUserByName(name);
        log.info(" > User authenticated successfully!");
        return ResponseEntity.ok(authenticatedUser);
    }


    /**
     * Creates a new MEMBER and enrolls them in an existing team if the entered activity exists.
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
        User addedMember = userService.createAndAddMemberToTeam(username, teamName);
        return ResponseEntity.ok(addedMember);
    }


    /**
     * Creates a new MENTOR and enrolls them in a new or existing activity.
     *
     * @param userName     The username of the new mentor to be created.
     * @param activityName The name of the activity to be linked with the mentor.
     * @param dueDate      The due date for the mentor's activity.
     * @return A ResponseEntity containing the User object representing the newly created mentor.
     */
    @Operation(summary = "creates new mentor and enrolls him in new or existing activity")
    @PostMapping("/new/mentor")
    public ResponseEntity<User> createMentorAndAddMentor(@RequestParam String userName,
                                                         @RequestParam String activityName,
                                                         @RequestParam String dueDate) {
        log.info(" > Creating a new mentor with username: {}", userName);
        User newMentor = userService.createNewUser(userName, Role.MENTOR);
        // to handle the new mentor linking to existing/new activity
        activityService.addNewMentorToActivity(activityName, newMentor, dueDate);
        return ResponseEntity.ok(newMentor);
    }


    /**
     * Creates a new LEAD and a new team for them.
     *
     * @param userName The username of the new lead to be created.
     * @param teamName The name of the new team to be created and associated with the lead.
     * @return A ResponseEntity containing the User object representing the newly created lead.
     */
    @Operation(summary = "creates new lead and creates a new team for him")
    @PostMapping("/new/lead")
    public ResponseEntity<User> createLeadAndAddLead(@RequestParam String userName,
                                                     @RequestParam String teamName) {
        log.info(" > Creating a new lead with username: {} and adding to team: {}", userName, teamName);
        User user = userService.createAndAddLeadToTeam(userName, teamName);
        return ResponseEntity.ok(user);
    }
}

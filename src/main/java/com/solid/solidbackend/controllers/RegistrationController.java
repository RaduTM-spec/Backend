package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.MentorActivityService;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.implementations.TeamServiceImpl;
import com.solid.solidbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/")
public class RegistrationController {

    // here you inject the service beans that you use
    private final UserService userService;
    private final TeamService teamService;
    private final MentorActivityService mentorActivityService;
    private final ActivityService activityService;

    public RegistrationController(UserService userService, TeamServiceImpl teamService, MentorActivityService mentorActivityService, ActivityService activityService)
    {
        this.userService = userService;
        this.teamService = teamService;
        this.mentorActivityService = mentorActivityService;
        this.activityService = activityService;
    }

    @PostMapping("/")
    public ResponseEntity<User> authenticateUser(String name) {
        // Logic to handle an existing user authentication
        // This appears if users selects he is an existing user
        // We consider that if the user sends this post, 100% he exists in the database (from IBM specs)
        // So this will always retrun a user obj

        User x = userService.getUserByName(name);
        return ResponseEntity.ok(x);
    }

    @PostMapping("/new/member/{teamName}")
    public ResponseEntity<User> createMemberAndAddMemberToTeam(@RequestBody String username, @PathVariable String teamName) {
        User u = userService.createNewUser(username, Role.MEMBER);
        teamService.addUserToTeam(u.getName(), teamName);
        return ResponseEntity.ok(u);
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
    @PostMapping("/new/mentor/{activityName}/{dueDate}")
    public ResponseEntity<User> createMentorAndAddMentor(@RequestBody  String userName,
                                                        @PathVariable String activityName,
                                                        @PathVariable String dueDate) {

        User newMentor = userService.createNewUser(userName, Role.MENTOR);


        // to handle the new mentor linking to existing/new activity
        this.activityService.addNewMentorToActivity(activityName, newMentor, dueDate);

        return ResponseEntity.ok(newMentor);
    }


    /**
     * 1. Creates a new leader
     * 2. If the team already exists, its bad
     *    Else it creates a team and a membership it;
     * @param user
     * @param teamName
     * @return
     */
    @PostMapping("/new/lead/{teamName}")
    public ResponseEntity<User> createLeadAndAddLead(@RequestBody  String userName,
                                                     @PathVariable String teamName) {
        User u = userService.createNewUser(userName, Role.MEMBER);
        Team team = teamService.createTeam(teamName, u);
        teamService.createTeam(team.getName(), u);
        teamService.addUserToTeam(u.getName(), team.getName());
        return ResponseEntity.ok(u);
    }


}
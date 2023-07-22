package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/")
public class RegistrationController {

    // here you inject the service beans that you use
    private final UserService userService;
    private final TeamService teamService;
    public RegistrationController(UserService userService, TeamService teamService)
    {
        this.userService = userService;
        this.teamService = teamService;
    }

    @PostMapping("/")
    public ResponseEntity<User> authenticateUser(@RequestBody User user) {
        // Logic to handle an existing user authentication
        // This appears if users selects he is an existing user
        // We consider that if the user sends this post, 100% he exists in the database (from IBM specs)
        // So this will always retrun a user obj

        User x = userService.getUserByName(user.getName());
        return ResponseEntity.ok(x);
    }

    @PostMapping("/new/member/{teamName}")
    public ResponseEntity<User> addUserToTeam(@RequestBody User user, @PathVariable String teamName) {

        teamService.addUserToTeam(user, teamName);
        return null;
    }



  /*  @PostMapping("/new/mentor/{activityName}/{dueDate}")
    public ResponseEntity<Optional<User>> addMentor(@RequestBody User user,
                                                    @PathVariable String activityName,
                                                    @PathVariable String dueDate) {
        // Logic to handle new mentor registration
        return null;
    }

    @PostMapping("/new/lead/{teamName}")
    public ResponseEntity<User> addLead(@RequestBody User user,
                                        @PathVariable String teamName) {
        // Logic to handle new user registration if he is a team lead
        // Return appropriate response, this should always return a User object
        // We consider that neither the teaam name nor the user already exist

        return null;
    }
*/

}
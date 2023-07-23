package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@RequestMapping("/")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/")
    public ResponseEntity<User> authenticateUser(@RequestBody User user) {
        User loggedInUser = this.userService.getUserByName(user.getName());
        return ResponseEntity.ok(loggedInUser);
    }



    @PostMapping("/new/member/{teamName}")
    public ResponseEntity<User> addUser(@RequestBody User user,
                                        @PathVariable String activityName) {
        // Logic to handle new user registration if he selects he is a member
        // The user should be considered as non existent
        return null;
    }

    @PostMapping("/new/mentor/{activityName}/{dueDate}")
    public ResponseEntity<Optional<User>> addMentor(@RequestBody  String userName,
                                                    @PathVariable String activityName,
                                                    @PathVariable String dueDate) {
        User loggedInMentor = userService.getUserByName(userName);




        // Logic to handle new mentor registration
        return null;
    }

    @PostMapping("/new/mentor/{activityName}")
    public ResponseEntity<User> addLead(@RequestBody String userName,
                                        @PathVariable String activityName) {
        // Logic to handle new user registration if he is a team lead
        // Return appropriate response, this should always return a User object
        // We consider that neither the teaam name nor the user already exist

        return null;
    }


}
package com.solid.solidbackend.Controllers;

import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@RequestMapping("/")
public class RegistrationController {

    // here you inject the service beans that you use


    @PostMapping("/")
    public ResponseEntity<User> authenticateUser(@RequestBody User user) {
        // Logic to handle an existing user authentication
        // This appears if users selects he is an existing user
        // We consider that if the user sends this post, 100% he exists in the database (from IBM specs)
        // So this will always retrun a user obj
        return null;
    }

    @PostMapping("/new/member/{activityName}")
    public ResponseEntity<User> addUser(@RequestBody User user,
                                                  @PathVariable String activityName) {
        // Logic to handle new user registration if he selects he is a member
        // The user should be considered as non existent
        return null;
    }

    @PostMapping("/new/mentor/{activityName}/{dueDate}")
    public ResponseEntity<Optional<User>> addMentor(@RequestBody User user,
                                                    @PathVariable String activityName,
                                                    @PathVariable String dueDate) {
        // Logic to handle new mentor registration
        return null;
    }

    @PostMapping("/new/lead/{activityName}")
    public ResponseEntity<User> addLead(@RequestBody User user,
                                                  @PathVariable String teamName) {
        // Logic to handle new user registration if he is a team lead
        // Return appropriate response, this should always return a User object
        // We consider that neither the teaam name nor the user already exist

        return null;
    }


}

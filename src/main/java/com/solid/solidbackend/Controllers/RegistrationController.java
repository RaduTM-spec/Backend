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
    public ResponseEntity<Optional<User>> authenticateUser(@RequestBody User user) {
        // Logic to handle an existing user authentication
        // This appears if users selects he is an existing user
        // Check if username exists in the database
        // If the user is already registered with a role, return the User object from db(search by username)
        // If it does not exist, return nothing(research optionals) and put 401 status code

        return null;
    }

    @PostMapping("/new/member/{activityName}")
    public ResponseEntity<Optional<User>> addUser(@RequestBody User user,
                                                  @PathVariable String activityName) {
        // Logic to handle new user registration if he selects he is a member
        // If for some reason the user exists already you should not create the user
        // !! If activityName DOES NOT exist you should not create the user, because he has no team to join
        // Return appropriate response, this should almost always return a User object, exception being the case in

        return null;
    }

    @PostMapping("/new/mentor/{activityName}/{dueDate}")
    public ResponseEntity<Optional<User>> addMentor(@RequestBody User user,
                                                    @PathVariable String activityName,
                                                    @PathVariable String dueDate) {
        // Logic to handle new mentor registration
        // If for some reason the user exists already you should not create the user
        // If activityName ALREADY exists you should not create the user
        // Return appropriate response, this should almost always return a User object

        return null;
    }

    @PostMapping("/new/lead/{teamName}")
    public ResponseEntity<Optional<User>> addLead(@RequestBody User user,
                                                  @PathVariable String teamName) {
        // Logic to handle new user registration if he is a team lead
        // If for some reason the user exists already you should not create the user
        // If teamName already exists you should not create the user
        // Return appropriate response, this should almost always return a User object

        return null;
    }


}

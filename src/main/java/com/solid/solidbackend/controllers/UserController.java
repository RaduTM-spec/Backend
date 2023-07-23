package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/activities/{userName}")
    public ResponseEntity<List<Activity>> getActivities(@PathVariable String userId) {


        // TO DO 2;
        return null;
    }


    @GetMapping("/activities/{userName}/{activityName}/teams")
    public ResponseEntity<List<Team>> getActivityTeams(@PathVariable String activityName) {
        // Logic to fetch and return all teams linked to the specified activity

        // TO DO 2;
        return null;
    }

    @GetMapping("/activities/{userName}/assessments")
    public ResponseEntity<List<Assessment>> getUserAssessments(@PathVariable Long userName) {
        // Logic to fetch and return all activities and grades, as well as comments for the given user
        // This is used only by the team lead and members
        // returns an optional because the repository can return the value null

        // TO DO 2;

        return null;
    }

    @PostMapping("/activities/{userName}/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity mentorActivity) {
        // Logic to create a new activity from the perspective of a mentor
        // If no activity linked to the mentor exists in the database, prompt for creation
        // Return appropriate response

        // TO DO 2;
        return null;
    }

    @PutMapping("/activities/{userName}/join/{activityName}")
    public ResponseEntity<Activity> joinExistingActivity(@PathVariable String activityName) {

        // Self explanatory
        // can only be used by team leader and mentor
        // returns optional because the validation can go wrong

        // TO DO 2;
        return null;
    }



    @GetMapping("/activities/{userName}/{activityName}/teams/{teamName}")
    public ResponseEntity<Team> getTeamDetails(@PathVariable String teamName) {
        // Logic to fetch and return team information with all members for the specified activity
        // Make sure to also return a list of members in some way



        return null;
    }
}
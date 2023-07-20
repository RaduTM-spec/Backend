package com.solid.solidbackend.Controllers;

import com.solid.solidbackend.Entities.Activity;
import com.solid.solidbackend.Entities.Assessment;
import com.solid.solidbackend.Entities.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("/activities/{userId}")
    public ResponseEntity<List<Activity>> getActivities(@PathVariable String userId) {
        // Logic to fetch and return a list of activities already joined by the user by the user id sent

        return null;
    }

    @GetMapping("/activities/{userId}/{activityName}/teams")
    public ResponseEntity<List<Team>> getActivityTeams(@PathVariable String activityName) {
        // Logic to fetch and return all teams linked to the specified activity
        return null;
    }

    @GetMapping("/activities/{userId}/assessments")
    public ResponseEntity<List<Assessment>> getUserAssessments(@PathVariable String userId) {
        // Logic to fetch and return all activities and grades, as well as comments for the given user
        // This is used only by the team lead and members
        // returns an optional because the repository can return the value null
        return null;
    }

    @PostMapping("/activities/{userId}/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity mentorActivity) {
        // Logic to create a new activity from the perspective of a mentor
        // If no activity linked to the mentor exists in the database, prompt for creation
        // Return appropriate response
        return null;
    }


    @PutMapping("/activities/{userId}/join/{activityName}")
    public ResponseEntity<Activity> joinExistingActivity(@PathVariable String activityName) {

        // Self explanatory
        // can only be used by team leader and mentor
        // returns optional because the validation can go wrong
        return null;
    }



    @GetMapping("/activities/{userId}/{activityName}/teams/{teamName}")
    public ResponseEntity<Team> getTeamDetails(@PathVariable String activityName,
                                               @PathVariable String teamName) {
        // Logic to fetch and return team information with all members for the specified activity
        // Make sure to also return a list of members in some way
        return null;
    }
}
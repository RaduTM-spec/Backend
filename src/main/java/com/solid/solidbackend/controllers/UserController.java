package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.TeamDetails;
import com.solid.solidbackend.services.AssessmentService;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final TeamService teamService;
    private final AssessmentService assessmentService;

    @Autowired
    public UserController(UserService userService, TeamService teamService, AssessmentService assessmentService) {
        this.userService = userService;
        this.teamService = teamService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/activities/{userName}")
    public ResponseEntity<List<Activity>> getActivities(@PathVariable String userName) {
        List<Activity> activities = userService.getUserActivities(userName);
        return ResponseEntity.ok(activities);
    }


    @GetMapping("/activities/{userName}/{activityName}/teams")
    public ResponseEntity<List<Team>> getActivityTeams(@PathVariable String activityName) {
        // Logic to fetch and return all teams linked to the specified activity

        List<Team> teams = teamService.getTeamsByActivity(activityName);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/activities/{userName}/assessments")
    public ResponseEntity<List<Assessment>> getUserAssessments(@PathVariable String userName) {
        // Logic to fetch and return all activities and grades, as well as comments for the given user
        // This is used only by the team lead and members
        // returns an optional because the repository can return the value null

        List<Assessment> assessments = assessmentService.getUserAssessments(userName);
        return ResponseEntity.ok(assessments);
    }

    @PostMapping("/activities/{userName}/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity mentorActivity) {
        // Logic to create a new activity from the perspective of a mentor
        // If no activity linked to the mentor exists in the database, prompt for creation
        // Return appropriate response
        Activity activity = userService.createActivity(mentorActivity);
        return ResponseEntity.ok(activity); // TODO idk what to return
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
    public ResponseEntity<TeamDetails> getTeamDetailsFromAnActivity(@PathVariable String activityName,
                                                                    @PathVariable String teamName) {
        TeamDetails td = teamService.getTeamDetailsFromAnActivity(activityName, teamName);
        return ResponseEntity.ok(td);
    }
}
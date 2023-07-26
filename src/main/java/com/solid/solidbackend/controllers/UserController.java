package com.solid.solidbackend.controllers;

import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.AssessmentService;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final TeamService teamService;
    private final AssessmentService assessmentService;
    private final ActivityService activityService;

    @Autowired
    public UserController(UserService userService, TeamService teamService, AssessmentService assessmentService, ActivityService activityService) {
        this.userService = userService;
        this.teamService = teamService;
        this.assessmentService = assessmentService;
        this.activityService = activityService;
    }

    @Operation(summary = "gets all activities enrolled by the user")
    @GetMapping("/activities/{userName}")
    public ResponseEntity<List<Activity>> getActivities(@PathVariable String userName) {
        log.info(" > Fetching activities joined by user: {}", userName);
        List<Activity> activities = activityService.getUserActivities(userName);
        return ResponseEntity.ok(activities);
    }

    @Operation(summary = "gets all teams enrolled in a specific activity")
    @GetMapping("/activities/{userName}/{activityName}/teams")
    public ResponseEntity<List<Team>> getActivityTeams(@PathVariable String activityName) {
        log.info(" > Fetching teams registered in activity: {}", activityName);
        List<Team> teams = teamService.getTeamsByActivity(activityName);
        return ResponseEntity.ok(teams);
    }

    @Operation(summary = "gets all assessments received by a user in activities")
    @GetMapping("/activities/{userName}/assessments")
    public ResponseEntity<List<Assessment>> getUserAssessments(@PathVariable String userName) {
        log.info(" > Fetching assessments for user: {}", userName);
        List<Assessment> assessments = assessmentService.getUserAssessments(userName);
        return ResponseEntity.ok(assessments);
    }

    @Operation(summary = "creates an activity and enrolls the user in it")
    @PostMapping("/activities/{userName}/create")
    public ResponseEntity<Activity> createActivity(@PathVariable String userName,
                                                   @RequestBody Activity mentorActivity) {

        log.info(" > Creating a new activity named {} for mentor: {}", mentorActivity.getName(), userName);
        Activity activity = activityService.createActivity(mentorActivity);
        return ResponseEntity.ok(activity);
    }

    @Operation(summary = "joins the team of a user in an existing activity")
    @PutMapping("/activities/{userName}/join/{activityName}")
    public ResponseEntity<Activity> joinExistingActivity(@PathVariable String userName,
                                                         @PathVariable String activityName) {

        log.info(" > Joining existing activity: {} by user: {}", activityName, userName);
        Activity joinedActivity = activityService.joinActivity(userName, activityName);
        return ResponseEntity.ok(joinedActivity);
    }


    // TODO this needs careful refactoring. it returns an empty list of members even if members joined
    @Operation(summary = "gets all the details of the team and its performance in an activity")
    @GetMapping("/activities/{userName}/{activityName}/teams/{teamName}")
    public ResponseEntity<TeamDetails> getTeamDetailsFromAnActivity(@PathVariable String activityName,
                                                                    @PathVariable String teamName,
                                                                    @PathVariable String userName) {

        log.info(" > Fetching team details for activity: {} and team: {}", activityName, teamName);
        TeamDetails td = teamService.getTeamDetailsFromAnActivity(activityName, teamName);
        return ResponseEntity.ok(td);
    }

    @Operation(summary = "creates a new session assessment for a team in an activity")
    @PostMapping("/activities/{userName}/{activityName}/teams/{teamName}/assessment")
    public ResponseEntity<TeamDetails> createNewSessionAssessmentOnTeam(@PathVariable String userName,
                                                                         @PathVariable String activityName,
                                                                         @PathVariable String teamName,
                                                                         @RequestBody List<Assessment> newAssessments) {
        User mentor = userService.getUserByName(userName);
        assessmentService.saveAssessmentsToActivity(activityName, mentor, newAssessments);

        TeamDetails updatedTeamDetails = teamService.getTeamDetailsFromAnActivity(activityName, teamName);
        return ResponseEntity.ok(updatedTeamDetails);
    }


}
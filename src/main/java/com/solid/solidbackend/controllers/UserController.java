package com.solid.solidbackend.controllers;

import com.solid.solidbackend.dtos.TeamDetailsDTO;
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


/**
 * Controller for user registration and authentication operations.
 */

@Slf4j
@RestController
@CrossOrigin("*")
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

    /**
     * Fetches all activities enrolled by a specific user.
     *
     * @param userName The username of the user whose activities are to be fetched.
     * @return A ResponseEntity containing a list of Activity objects representing the activities joined by the user.
     */
    @GetMapping("/activities")
    @Operation(summary = "fetches all activities enrolled by the user")
    public ResponseEntity<List<Activity>> getActivities(@RequestParam String userName) {
        log.info(" > Fetching activities joined by user: {}", userName);
        List<Activity> activities = activityService.getUserActivities(userName);
        return ResponseEntity.ok(activities);
    }


    /**
     * Fetches all teams enrolled in a specific activity for a given user.
     *
     * @param userName     The username of the user for whom the teams are to be fetched.
     * @param activityName The name of the activity for which the teams are to be fetched.
     * @return A ResponseEntity containing a list of Team objects representing the teams registered in the activity.
     */
    @Operation(summary = "gets all teams enrolled in a specific activity")
    @GetMapping("/activity-teams")
    public ResponseEntity<List<Team>> getActivityTeams(@RequestParam String userName,
                                                       @RequestParam String activityName) {
        userService.checkIfUserIsMentor(userName);
        log.info(" > Fetching teams registered in activity: {}", activityName);
        List<Team> teams = teamService.getTeamsByActivity(userName, activityName);
        return ResponseEntity.ok(teams);
    }


    @Operation(summary = "gets all assessments received by a user in activities")
    @GetMapping("/user-assessments")
    public ResponseEntity<List<Assessment>> getUserAssessments(@RequestParam String userName) {
        log.info(" > Fetching assessments for user: {}", userName);
        List<Assessment> assessments = assessmentService.getUserAssessments(userName);
        return ResponseEntity.ok(assessments);
    }

    @Operation(summary = "creates an activity and enrolls the user in it")
    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivity(@RequestParam String userName,
                                                   @RequestBody Activity mentorActivity) {
        userService.checkIfUserIsMentor(userName);
        log.info(" > Creating a new activity named {} for mentor: {}", mentorActivity.getName(), userName);
        Activity activity = activityService.createAndJoinActivity(userName, mentorActivity);
        return ResponseEntity.ok(activity);
    }

    @Operation(summary = "joins the user or the team of a user in an existing activity")
    @PutMapping("/join-activity")
    public ResponseEntity<Activity> joinExistingActivity(@RequestParam String userName,
                                                         @RequestParam String activityName) {
        // no need to check role here because the JoinActivity method`s logic handle all possible ROLE cases
        log.info(" > Joining existing activity: {} by user: {}", activityName, userName);
        Activity joinedActivity = activityService.joinActivity(userName, activityName);
        return ResponseEntity.ok(joinedActivity);
    }

    @GetMapping("/team-details")
    @Operation(summary = "gets all the details of the team and its performance in an activity")
    public ResponseEntity<TeamDetailsDTO> getTeamDetailsFromAnActivity(@RequestParam String mentorUserName,
                                                                       @RequestParam String activityName,
                                                                       @RequestParam String teamName) {

        log.info(" > Fetching team details for activity: {} and team: {}", activityName, teamName);
        TeamDetailsDTO td = teamService.getTeamDetailsFromAnActivity(activityName, teamName);
        return ResponseEntity.ok(td);
    }



    @Operation(summary = "creates a new session assessment for a team in an activity")
    @PostMapping("/team-assessment")
    public ResponseEntity<TeamDetailsDTO> createNewSessionAssessmentOnTeam(@RequestParam String mentorUserName,
                                                                           @RequestParam String activityName,
                                                                           @RequestParam String teamName,
                                                                           @RequestBody List<Assessment> newAssessments) {
        // Actually allows multiple assignments in a row for a team.
        userService.checkIfUserIsMentor(mentorUserName);
        User mentor = userService.findUserByName(mentorUserName);
        assessmentService.saveAssessmentsToActivity(activityName, mentor, newAssessments);
        TeamDetailsDTO updatedTeamDetailsDTO = teamService.getTeamDetailsFromAnActivity(activityName, teamName);
        return ResponseEntity.ok(updatedTeamDetailsDTO);
    }

    @Operation(summary = "removes a member from a the user's team")
    @DeleteMapping("remove-member")
    public ResponseEntity<Boolean> removeMemberFromTeam(@RequestParam String teamLeaderName,
                                                        @RequestParam String removedMemberName,
                                                        @RequestParam String teamName) {
        log.info(" > Removing {} from {}", removedMemberName, teamName);
        teamService.removeMemberFromTeam(teamLeaderName, removedMemberName, teamName);
        return ResponseEntity.ok(true);
    }
}

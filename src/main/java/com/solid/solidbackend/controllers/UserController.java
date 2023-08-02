package com.solid.solidbackend.controllers;

import com.solid.solidbackend.dtos.TeamDetailsDTO;
import com.solid.solidbackend.dtos.TeamGradeDTO;
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
    @Operation(summary = "Fetches all activities enrolled by the user.")
    public ResponseEntity<List<Activity>> getActivities(@RequestParam String userName) {
        log.info(" > Fetching activities joined by user: {}", userName);
        List<Activity> activities = activityService.getUserActivities(userName);
        return ResponseEntity.ok(activities);
    }

    /**
     * Fetches all teams enrolled in a specific activity for a given mentor.
     *
     * @param mentorName   The username of the user for whom the teams are to be fetched.
     * @param activityName The name of the activity for which the teams are to be fetched.
     * @return A ResponseEntity containing a list of TeamGradeDTO objects representing the teams registered in the activity along with their grades.
     */
    @GetMapping("/activity-teams")
    @Operation(summary = "Fetches all teams enrolled in a specific activity for a given mentor.")
    public ResponseEntity<List<TeamGradeDTO>> getActivityTeamsAndTheirGrades(@RequestParam String mentorName, @RequestParam String activityName) {
        userService.checkIfUserIsMentor(mentorName);
        List<TeamGradeDTO> list = teamService.getActivityTeamsWithTheirGrades(activityName);
        return ResponseEntity.ok(list);
    }

    /**
     * Fetches all assessments received by a user in activities.
     *
     * @param userName The username of the user for whom the assessments are to be fetched.
     * @return A ResponseEntity containing a list of Assessment objects representing the assessments received by the user.
     */
    @GetMapping("/user-assessments")
    @Operation(summary = "Fetches all assessments received by a user in activities.")
    public ResponseEntity<List<Assessment>> getUserAssessments(@RequestParam String userName) {
        log.info(" > Fetching assessments for user: {}", userName);
        List<Assessment> assessments = assessmentService.getUserAssessments(userName);
        return ResponseEntity.ok(assessments);
    }

    /**
     * Creates an activity and enrolls the user in it.
     *
     * @param userName     The username of the mentor who creates the activity and enrolls the user.
     * @param activityName The name of the activity to be created.
     * @param deadline     The deadline for the activity.
     * @return A ResponseEntity containing an Activity object representing the created activity.
     */
    @PostMapping("/activities")
    @Operation(summary = "Creates an activity and enrolls the user in it.")
    public ResponseEntity<Activity> createActivity(@RequestParam String userName,
                                                   @RequestParam String activityName,
                                                   @RequestParam String deadline) {
        userService.checkIfUserIsMentor(userName);
        log.info(" > Creating a new activity named {} for mentor: {}", activityName, userName);
        Activity activity = activityService.createAndJoinActivity(userName, activityName, deadline);
        return ResponseEntity.ok(activity);
    }

    /**
     * Joins the user or the team of a user in an existing activity.
     *
     * @param userName     The username of the user who joins the activity.
     * @param activityName The name of the existing activity to be joined.
     * @return A ResponseEntity containing an Activity object representing the joined activity.
     */
    @PutMapping("/join-activity")
    @Operation(summary = "Joins the user or the team of a user in an existing activity.")
    public ResponseEntity<Activity> joinExistingActivity(@RequestParam String userName,
                                                         @RequestParam String activityName) {

        // No need to check the role here because the JoinActivity method's logic handles all possible ROLE cases.
        log.info(" > Joining existing activity: {} by user: {}", activityName, userName);
        Activity joinedActivity = activityService.joinActivity(userName, activityName);
        return ResponseEntity.ok(joinedActivity);
    }

    /**
     * Fetches all the details of the team and its performance in an activity.
     *
     * @param mentorUserName The username of the mentor who retrieves the team details.
     * @param activityName   The name of the activity for which the team details are fetched.
     * @param teamName       The name of the team for which the details are fetched.
     * @return A ResponseEntity containing a TeamDetailsDTO object representing the details of the team and its performance.
     */
    @GetMapping("/team-details")
    @Operation(summary = "Fetches all the details of the team and its performance in an activity.")
    public ResponseEntity<TeamDetailsDTO> getTeamDetailsFromAnActivity(@RequestParam String mentorUserName,
                                                                       @RequestParam String activityName,
                                                                       @RequestParam String teamName) {

        log.info(" > Fetching team details for activity: {} and team: {}", activityName, teamName);
        TeamDetailsDTO td = teamService.getTeamDetailsFromAnActivity(activityName, teamName);
        return ResponseEntity.ok(td);
    }

    /**
     * Creates a new session assessment for a team in an activity.
     *
     * @param mentorUserName  The username of the mentor who creates the assessment.
     * @param activityName    The name of the activity in which the assessment is created.
     * @param teamName        The name of the team for which the assessment is created.
     * @param newAssessments  A list of Assessment objects representing the new assessments to be added to the team.
     * @return A ResponseEntity containing a TeamDetailsDTO object representing the updated team details after adding the new assessments.
     */
    @PostMapping("/team-assessment")
    @Operation(summary = "Creates a new session assessment for a team in an activity.")
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

    /**
     * Removes a member from a team.
     *
     * @param teamLeaderName    The username of the team leader who removes a member.
     * @param removedMemberName The username of the member to be removed.
     * @param teamName          The name of the team from which the member is removed.
     * @return A ResponseEntity containing a boolean value indicating the success of the removal operation.
     */
    @DeleteMapping("remove-member")
    @Operation(summary = "Removes a member from the user's team.")
    public ResponseEntity<Boolean> removeMemberFromTeam(@RequestParam String teamLeaderName,
                                                        @RequestParam String removedMemberName,
                                                        @RequestParam String teamName) {
        log.info(" > Removing {} from {}", removedMemberName, teamName);
        teamService.removeMemberFromTeam(teamLeaderName, removedMemberName, teamName);
        return ResponseEntity.ok(true);
    }
}

package com.solid.solidbackend.Controllers;

import com.solid.solidbackend.Entities.Activity;
import com.solid.solidbackend.Entities.Assessment;
import com.solid.solidbackend.Entities.Team;
import com.solid.solidbackend.Entities.User;
import com.solid.solidbackend.Enums.Role;
import com.solid.solidbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/alsoDepricated")
public class MentorController{
//    private final UserService userService;
//    @Autowired
//    public MentorController(UserService userService){
//        this.userService = userService;
//    }
//
//
//    /**
//     * Searches for the team of the member, afterwards for activity_memberships, then selects all activities
//
//     * @return
//     */
//    @GetMapping("/activities/{userId}")
//    public ResponseEntity<List<Activity>> getActivities(@PathVariable Long userId)
//    {
//        return null;
//    }
//
//    /**
//     * Saves the activity in the DB.
//     * @param activity
//     * @return
//     */
//    @PostMapping(path = "/activities/create")
//    public ResponseEntity<Activity> createActivity(Activity activity)
//    {
//        return null;
//    }
//
//    /**
//     * Joins an activity as a mentor
//     * @param mentor
//     * @return
//     */
//    @PostMapping(path = "/activities/join/{activityId}")
//    public ResponseEntity<Activity> joinActivity(@PathVariable Long activityId, @RequestBody User mentor)
//    {
//        return null;
//    }
//
//    /**
//     * Get all teams that joined in a activity.
//     * @param
//     * @return
//     */
//    @GetMapping(path = "/activities/{activityId}")
//    public ResponseEntity<List<Team>> getTeams(@PathVariable Long activityId)
//    {
//        return null;
//    }
//
//    /**
//     * Returns all Users (members/leader) from a team.
//     * @param
//     * @return
//     */
//    @GetMapping(path = "/activities/teams/{teamId}")
//    public ResponseEntity<List<User>> getTeamMembers(@PathVariable Long teamId)
//    {
//        return null;
//    }
//
//    /**
//     * Get user assessment by id.
//     * @param userId
//     * @return
//     */
//    @GetMapping(path = "/activities/teams/assessment/{userId}")
//    public ResponseEntity<Assessment> getUserAssessment(@PathVariable Long userId)
//    {
//        return null;
//    }
//
//    @PostMapping(path = "/activities/teams/assessment/{userId}")
//    public ResponseEntity<Assessment> giveUserAssessment(@PathVariable Long userId)
//    {
//        return null;
//    }
}

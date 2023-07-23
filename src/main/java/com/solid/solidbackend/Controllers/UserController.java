package com.solid.solidbackend.Controllers;

<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
import com.solid.solidbackend.Entities.Activity;
import com.solid.solidbackend.Entities.Assessment;
import com.solid.solidbackend.Entities.Team;
=======
import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.Team;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.services.AssessmentService;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
import java.util.Optional;
=======
import java.util.Objects;
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java

@RestController
@RequestMapping("/")
public class UserController {

<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
=======
    private final UserService userService;
    private final TeamService teamService;
    private final AssessmentService assessmentService;

    @Autowired
    public UserController(UserService userService, TeamService teamService, AssessmentService assessmentService) {
        this.userService = userService;
        this.teamService = teamService;
        this.assessmentService = assessmentService;
    }

>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
    @GetMapping("/activities/{userId}")
    public ResponseEntity<List<Activity>> getActivities(@PathVariable Long userId) {
        // Logic to fetch and return a list of activities already joined by the user by the user id sent

<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
        return null;
=======
        // TODO this broken
        List<Activity> activities = userService.getUserActivities(userId);
        return ResponseEntity.ok(activities);
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
    }

    @GetMapping("/activities/{userId}/{activityName}/teams")
    public ResponseEntity<List<Team>> getActivityTeams(@PathVariable String activityName) {
        // Logic to fetch and return all teams linked to the specified activity
<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
        return null;
=======

        // TODO this also broken
        List<Team> teams = teamService.getTeamsByActivity(activityName);
        return ResponseEntity.ok(teams);
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
    }

    @GetMapping("/activities/{userId}/assessments")
    public ResponseEntity<Optional<List<Assessment>>> getUserAssessments(@PathVariable String userId) {
        // Logic to fetch and return all activities and grades, as well as comments for the given user
        // This is used only by the team lead and members
        // returns an optional because the repository can return the value null
<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
        return null;
=======

        List<Assessment> assessments = assessmentService.getUserAssessments(userId);
        return ResponseEntity.ok(assessments);
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
    }

    @PostMapping("/activities/{userId}/create")
    public ResponseEntity<Optional<Activity>> createActivity(@RequestBody Activity mentorActivity) {
        // Logic to create a new activity from the perspective of a mentor
        // If no activity linked to the mentor exists in the database, prompt for creation
        // Return appropriate response
<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
        return null;
=======

        return ResponseEntity.ok(userService.createActivity(mentorActivity));
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
    }

    @PutMapping("/activities/{userId}/join/{activityName}")
    public ResponseEntity<Optional<Activity>> joinExistingActivity(@PathVariable String activityName) {

        // Self explanatory
        // can only be used by team leader and mentor
        // returns optional because the validation can go wrong
        return null;
    }



    @GetMapping("/activities/{userId}/{activityName}/teams/{teamName}")
    public ResponseEntity<TeamTransferObject> getTeamDetails(@PathVariable String activityName,
                                                             @PathVariable String teamName) {
        // Logic to fetch and return team information with all members for the specified activity
<<<<<<< Updated upstream:src/main/java/com/solid/solidbackend/Controllers/UserController.java
        // Make sure to also return a list of members in some way\
        return null;

=======
        // Make sure to also return a list of members in some way


        List<Team> teams = teamService.getTeamsByActivity(activityName);
        TeamTransferObject teamTransferObject = null;
        for (Team team : teams) {
            if (Objects.equals(team.getName(), teamName)) {
                teamTransferObject = new TeamTransferObject(team.getId(), team.getName(), team.getLeader());
                break;
            }
        }

        teamTransferObject.setMembers(teamService.getTeamMembers(teamTransferObject.getId()));

        return ResponseEntity.ok(teamTransferObject);
    }
}


//This is a failed attempt at trying to display nicely the team and it's members
class TeamTransferObject {
    private Long id;
    private String name;
    private User leader;
    private List<User> members;

    public TeamTransferObject() {
    }

    public TeamTransferObject(Long id, String name, User leader) {
        this.id = id;
        this.name = name;
        this.leader = leader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
>>>>>>> Stashed changes:src/main/java/com/solid/solidbackend/controllers/UserController.java
    }


}
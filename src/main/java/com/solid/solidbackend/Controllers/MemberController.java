package com.solid.solidbackend.Controllers;

import com.solid.solidbackend.Entities.Activity;
import com.solid.solidbackend.Entities.Assessment;
import com.solid.solidbackend.Entities.User;
import com.solid.solidbackend.Enums.Role;
import com.solid.solidbackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class MemberController{
    private final UserService userService;
    @Autowired
    public MemberController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a new User (if doesn't exist), or extracts it from the DB
     * @param username
     * @param role
     * @return
     */
    @PostMapping
    public ResponseEntity<User> InitializeUser(String username, Role role)
    {
        return null;
    }

    /**
     * Searches for the team of the member, afterwards for activity_memberships, then selects all activities
     * @param user
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Activity>> GetActivities(@RequestBody User user)
    {
        return null;
    }

    /**
     * Get all assessments of a member.
     * @param
     * @return
     */
    @GetMapping("/activities/users/{userId}")
    public ResponseEntity<List<Assessment>> GetAssessments(@PathVariable Long userId)
    {
        return null;
    }
}

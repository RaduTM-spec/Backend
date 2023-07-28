package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.RoleNotAllowedException;
import com.solid.solidbackend.exceptions.NoActivityFoundException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
import com.solid.solidbackend.repositories.apprepository.*;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.MentorActivityService;
import com.solid.solidbackend.services.TeamActivityService;
import com.solid.solidbackend.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final MentorActivityService mentorActivityService;
    private final MentorActivityRepository mentorActivityRepository;
    private final UserRepository userRepository;
    private final TeamActivityRepository teamActivityRepository;
    private final TeamMembershipRepository teamMembershipRepository;
    private final TeamActivityService teamActivityService;
    private final UserService userService;

    public ActivityServiceImpl(ActivityRepository activityRepository, MentorActivityService mentorActivityService, MentorActivityRepository mentorActivityRepository, UserRepository userRepository,
                               TeamActivityRepository teamActivityRepository, TeamMembershipRepository teamMembershipRepository, TeamActivityService teamActivityService, UserService userService) {

        this.activityRepository = activityRepository;
        this.mentorActivityService = mentorActivityService;
        this.mentorActivityRepository = mentorActivityRepository;
        this.userRepository = userRepository;
        this.teamActivityRepository = teamActivityRepository;
        this.teamMembershipRepository = teamMembershipRepository;
        this.teamActivityService = teamActivityService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Activity createAndJoinActivity(String userName, Activity activity) {
        User mentor = userService.getUserByName(userName);

        Activity newActivity = new Activity();
        newActivity.setName(activity.getName());
        newActivity.setCreator(mentor);
        newActivity.setDeadline(activity.getDeadline());

        Activity savedActivity = activityRepository.save(newActivity);

        mentorActivityService.linkMentorWithActivity(mentor, savedActivity);

        return savedActivity;
    }

    @Override
    @Transactional
    public Activity getActivityByName(String activityName) {
        return activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException(activityName)
        );
    }

//    @Override
//    public List<Activity> getUserActivities(String userName) {
//        // Get user's team
//        User user = userRepository.findByName(userName).orElseThrow(
//                () -> new UserNotFoundException(userName)
//        );
//
//        if (Objects.requireNonNull(userService.checkUserRole(user)) == Role.MENTOR) {
////            userActivities.addAll(activityRepository.findActivitiesByCreator(user));
//
//            return new ArrayList<>(mentorActivityRepository.findActivitiesByMentor(user));
//
//        }
//
//        Team team = teamMembershipRepository.findTeamByUserId(user.getId());
//
//        // Use team's id to retrieve all activities
//        List<TeamActivity> teamActivities = teamActivityRepository.findAllActivitiesByTeamId(team.getId());
//        return teamActivities.stream().map(TeamActivity::getActivity).toList();
//
//    }

    @Override
    public List<Activity> getUserActivities(String userName) {
        User user = userService.getUserByName(userName);

        if (user.getRole() == Role.MENTOR) {
            return activityRepository.findActivitiesByUser(user);
        }

        Team team = teamMembershipRepository.findTeamByUserId(user.getId());
        List<TeamActivity> teamActivities = teamActivityRepository.findAllActivitiesByTeamId(team.getId());
        return teamActivities.stream().map(TeamActivity::getActivity).toList();
    }

    @Override
    @Transactional
    public MentorActivity addNewMentorToActivity(String activityName, User newMentor, String dueDate) {

        // search for the activity to see if it already exists
        Optional<Activity> existingActivity = activityRepository.findActivityByName(activityName);

        // if it does, we add the new mentor to it, else we create a new activity and add the mentor to it
        if (existingActivity.isPresent()) {
            return mentorActivityService.linkMentorWithActivity(newMentor, existingActivity.get());
        } else {
            Activity newActivity = new Activity();
            newActivity.setName(activityName);
            newActivity.setCreator(newMentor);
            newActivity.setDeadline(dueDate);

            Activity joinedActivity = activityRepository.save(newActivity);

            return mentorActivityService.linkMentorWithActivity(newMentor, joinedActivity);
        }

    }

    @Override
    public Activity joinActivity(String userName, String activityName) {
        // we get the activity that the lead wants to join
        Activity joinedActivity = activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException(activityName)
        );

        // we get the user for its id to use at linking
        User joiningUser = userRepository.findByName(userName).orElseThrow(
                () -> new UserNotFoundException(userName)
        );

        switch (userService.checkUserRole(joiningUser)) {
            case MENTOR -> mentorActivityService.linkMentorWithActivity(joiningUser, joinedActivity);
            case TEAM_LEADER -> {
                Team joiningTeam = teamMembershipRepository.findTeamByUserId(joiningUser.getId());
                teamActivityService.joinActivityByTeam(joinedActivity, joiningTeam);
            }
            default -> {
                throw new RoleNotAllowedException("MENTORS and LEADS");
            }
        }

        return joinedActivity;
    }

}

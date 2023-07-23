package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.exceptions.NoActivityFoundException;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.services.ActivityService;
import org.springframework.stereotype.Service;


@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }



    public MentorActivity addMentorToExistingActivity(String mentorName, String activityName, String dueDate) {

        Activity existingActivity = getActivityByName(activityName);



    }



    public Activity getActivityByName(String activityName) {

        return activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException("No activity was found with name: " + activityName)
        );

    }



}

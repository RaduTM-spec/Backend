package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.exceptions.NoActivityFoundException;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.repositories.apprepository.MentorActivityRepository;
import com.solid.solidbackend.services.ActivityService;
import org.springframework.stereotype.Service;


@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final MentorActivityRepository mentorActivityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, MentorActivityRepository mentorActivityRepository) {
        this.activityRepository = activityRepository;
        this.mentorActivityRepository = mentorActivityRepository;
    }


    /**
     * Adds mentor to an existing activity
      * @param mentorName
     * @param activityName
     * @param dueDate
     * @return
     */
    public MentorActivity joinMentorToActivity(String mentorName, String activityName, String dueDate) {

//        Activity existingActivity = getActivityByName(activityName);
//
//
//
//        MentorActivity newMentorActivity = new MentorActivity()
//
//        mentorActivityRepository.save();

        return null;

    }



    public Activity getActivityByName(String activityName) {

        return activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException("No activity was found with name: " + activityName)
        );

    }



}

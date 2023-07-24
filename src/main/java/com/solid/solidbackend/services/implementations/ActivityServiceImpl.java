package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.exceptions.NoActivityFoundException;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.repositories.apprepository.MentorActivityRepository;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.MentorActivityService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final MentorActivityService mentorActivityService;

    public ActivityServiceImpl(ActivityRepository activityRepository, MentorActivityRepository mentorActivityRepository, MentorActivityService mentorActivityService) {
        this.activityRepository = activityRepository;
        this.mentorActivityService = mentorActivityService;

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


    @Override
    public Activity getActivityByName(String activityName) {

        return activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException("No activity was found with name: " + activityName)
        );

    }

    @Override
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

            // we do this because we need an activity object with an id already set
            Activity joinedActivity = activityRepository.save(newActivity);

            return mentorActivityService.linkMentorWithActivity(newMentor, joinedActivity);

        }


    }



}

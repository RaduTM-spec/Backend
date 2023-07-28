package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.repositories.apprepository.MentorActivityRepository;
import com.solid.solidbackend.services.MentorActivityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MentorActivityServiceImpl implements MentorActivityService {
    private final MentorActivityRepository mentorActivityRepository;
    private final ActivityRepository activityRepository;


    public MentorActivityServiceImpl(MentorActivityRepository mentorActivityRepository, ActivityRepository activityRepository) {
        this.mentorActivityRepository = mentorActivityRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    @Transactional
    public MentorActivity linkMentorWithActivity(User mentor, Activity activity) {
        MentorActivity mentorActivity = new MentorActivity();
        mentorActivity.setMentor(mentor);
        mentorActivity.setActivity(activity);

        // We save the mentorActivity instance to the database
        return mentorActivityRepository.save(mentorActivity);
    }


//    @Transactional
//    public MentorActivity linkMentorWithActivity(User mentor, Activity activity) {
//        MentorActivity mentorActivity = new MentorActivity();
//
//        mentorActivity.setMentor(mentor);
//        mentorActivity.setActivity(activity);
//
//        activity.getMentors().add(mentor);
//        activityRepository.save(activity);
//
//        return mentorActivityRepository.save(mentorActivity);
//    }


}
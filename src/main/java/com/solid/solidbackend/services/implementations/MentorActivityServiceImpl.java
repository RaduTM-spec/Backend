package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.repositories.apprepository.MentorActivityRepository;
import com.solid.solidbackend.services.ActivityService;
import com.solid.solidbackend.services.MentorActivityService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentorActivityServiceImpl implements MentorActivityService {
    private final MentorActivityRepository mentorActivityRepository;


    public MentorActivityServiceImpl(MentorActivityRepository mentorActivityRepository) {
        this.mentorActivityRepository = mentorActivityRepository;
    }

    @Override
    public MentorActivity linkMentorWithActivity(User mentor, Activity activity) {
        MentorActivity mentorActivity = new MentorActivity();
        mentorActivity.setMentor(mentor);
        mentorActivity.setActivity(activity);
        return mentorActivityRepository.save(mentorActivity);
    }


}
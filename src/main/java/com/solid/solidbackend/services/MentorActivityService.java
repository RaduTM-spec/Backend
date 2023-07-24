package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.User;

public interface MentorActivityService {
    MentorActivity linkMentorWithActivity(User mentor, Activity activity);
}

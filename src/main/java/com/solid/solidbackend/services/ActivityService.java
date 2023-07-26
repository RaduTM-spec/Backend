package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.User;

import java.util.List;

public interface ActivityService {

    Activity getActivityByName(String activityName);

    List<Activity> getUserActivities(String userName);

    MentorActivity addNewMentorToActivity(String activityName, User newMentor, String dueDate);

    Activity createAndJoinActivity(String userName, Activity activity);

    Activity joinActivity(String userName, String activityName);
}

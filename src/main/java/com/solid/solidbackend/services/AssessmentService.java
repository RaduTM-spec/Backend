package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AssessmentService {


    List<Assessment> getAssessmentsByUsername(String userName);

    Assessment getAssessmentById(Long id);


    List<Assessment> getUserAssessments(String userName);

    void saveAssessmentsToActivity(String activityName, User mentor, List<Assessment> newAssessments);
}

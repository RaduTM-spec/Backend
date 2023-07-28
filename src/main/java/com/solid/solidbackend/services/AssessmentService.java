package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.exceptions.AssessmentNotFoundByIdException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AssessmentService {


    List<Assessment> getAssessmentsByUsername(String userName);

    Assessment getAssessmentById(Long id) throws AssessmentNotFoundByIdException;


    List<Assessment> getUserAssessments(String userName);

    void saveAssessmentsToActivity(String activityName, User mentor, List<Assessment> newAssessments);
}

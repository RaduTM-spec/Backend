package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AssessmentService {

    List<Assessment> getAllAssessments();

    List<Assessment> getAssessmentsByUserId(Long userId);

    List<Assessment> getAssessmentsByUserName(String userName);

    Assessment getAssessmentById(Long id);

    Assessment createAssessment(Assessment assessment);

    List<Assessment> getUserAssessments(String userName);
}

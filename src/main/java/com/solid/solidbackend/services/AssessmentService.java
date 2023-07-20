package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AssessmentService {

    List<Assessment> getAllAssessments();


    List<Assessment> getAssessmentsByUserId(Long userId);

    Assessment getAssessmentById(Long id);

    Assessment createAssessment(Assessment assessment);


}

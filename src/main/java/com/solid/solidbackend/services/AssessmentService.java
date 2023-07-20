package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public List<Assessment> getAssessmentsByUserId(Long userId)
    {
        Optional<List<Assessment>> assessments = assessmentRepository.findAllByUserId(userId);

        if(!assessments.isPresent())
            throw new IllegalStateException("No assessments found.");


        return assessments.get();
    }

}

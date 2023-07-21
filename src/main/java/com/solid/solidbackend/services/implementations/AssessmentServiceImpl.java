package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.repositories.apprepository.AssessmentRepository;
import com.solid.solidbackend.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public List<Assessment> getAllAssessments() {
        return null;
    }

    @Override
    public List<Assessment> getAssessmentsByUserId(Long userId)
    {
//        List<Assessment> assessments = assessmentRepository.findAllById(userId);
//
//        if(!assessments.isPresent())
//            throw new IllegalStateException("No assessments found.");
//
//
//        return assessments.get();
        return null;
    }

    @Override
    public Assessment getAssessmentById(Long id) {
        return null;
    }

    @Override
    public Assessment createAssessment(Assessment assessment) {
        return null;
    }

}

package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.repositories.apprepository.AssessmentRepository;
import com.solid.solidbackend.repositories.apprepository.UserRepository;
import com.solid.solidbackend.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, UserRepository userRepository) {
        this.assessmentRepository = assessmentRepository;
        this.userRepository = userRepository;
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


    @Override
    public List<Assessment> getUserAssessments(String userName) {
        User user = userRepository.findByName(userName).get(); // TODO error checking
        return assessmentRepository.findAllByUserId(user.getId());
    }

}

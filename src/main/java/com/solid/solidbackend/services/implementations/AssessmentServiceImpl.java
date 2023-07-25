package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.exceptions.AssessmentNotFoundException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
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
    public List<Assessment> getAssessmentsByUserName(String userName) {
        var userOpt = userRepository.findByName(userName);
        if(userOpt.isEmpty())
        {
            throw new UserNotFoundException(String.format("User %s not found when trying to get all of it's assessments", userName));
        }

        User user = userOpt.get();
        var assessments = assessmentRepository.findAllByUserId(user.getId());
        return assessments;
    }

    @Override
    public Assessment getAssessmentById(Long id) {
        var assOpt = assessmentRepository.findById(id);
        if(assOpt.isEmpty())
        {
            throw new AssessmentNotFoundException(String.format("Assessment with id %l not found.", id));
        }
        return assOpt.get();
    }

    @Override
    public Assessment createAssessment(Assessment assessment) {
        return null;
    }


    @Override
    public List<Assessment> getUserAssessments(String userName) {
        var userOpt = userRepository.findByName(userName);
        if(userOpt.isEmpty())
        {
            throw new UserNotFoundException(String.format("User %s not found when trying to get all of his assessments.", userName));
        }
        User user = userOpt.get();
        return assessmentRepository.findAllByUserId(user.getId());
    }

}

package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.*;
import com.solid.solidbackend.repositories.apprepository.ActivityRepository;
import com.solid.solidbackend.repositories.apprepository.AssessmentRepository;
import com.solid.solidbackend.repositories.apprepository.UserRepository;
import com.solid.solidbackend.services.AssessmentService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ActivityRepository activityRepository;

    @Autowired
    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, UserRepository userRepository, UserService userService, ActivityRepository activityRepository) {
        this.assessmentRepository = assessmentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.activityRepository = activityRepository;
    }


    @Override
    public List<Assessment> getAssessmentsByUsername(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(
                () -> new UserNotFoundException(userName)
        );

        return assessmentRepository.findAllByUserId(user.getId());
    }

    @Override
    public Assessment getAssessmentById(Long id) {
        return assessmentRepository.findById(id).orElseThrow(
                () -> new AssessmentNotFoundByIdException(id.toString())
        );
    }

    @Override
    public List<Assessment> getUserAssessments(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(
                () -> new UserNotFoundException(userName)
        );

        return assessmentRepository.findAllByUserId(user.getId());
    }

    @Override
    @Transactional
    public void saveAssessmentsToActivity(String activityName, User mentor, List<Assessment> newAssessments) {

//
//        if (userService.checkUserRole(mentor) != Role.MENTOR) {
//            throw new RoleNotAllowedException("Only MENTORS are allowed to send session assessments");
//        }

        Activity activity = activityRepository.findActivityByName(activityName)
                .orElseThrow(() -> new NoActivityFoundException(activityName));

        for (Assessment assessment : newAssessments) {
            assessment.setActivity(activity);

            User teamMember = userRepository.findByName(assessment.getUser().getName()).orElseThrow(
                    () -> new UserNotFoundException(assessment.getUser().getName()));

            assessment.setMentor(mentor);
            assessment.setUser(teamMember);

            assessmentRepository.save(assessment);
        }
    }

}

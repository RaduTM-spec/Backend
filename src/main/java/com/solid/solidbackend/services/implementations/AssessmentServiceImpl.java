package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.AssessmentNotFoundException;
import com.solid.solidbackend.exceptions.MembersNotAllowedException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
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


    @Override
    @Transactional
    public void saveAssessmentsToActivity(String activityName, User mentor, List<Assessment> newAssessments) {


        if (userService.checkUserRole(mentor) != Role.MENTOR) {
            throw new MembersNotAllowedException("Only MENTORS are allowed to send session assessments");
        }

        Activity activity = activityRepository.findActivityByName(activityName)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found"));

        for (Assessment assessment : newAssessments) {
            assessment.setActivity(activity);

            User teamMember = userRepository.findByName(assessment.getUser().getName()).orElseThrow(
                    () -> new UserNotFoundException("No user with name: "+ assessment.getUser().getName() + " was found"));

            assessment.setMentor(mentor);
            assessment.setUser(teamMember);

            assessmentRepository.save(assessment);
        }
    }

}

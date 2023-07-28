package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.exceptions.*;
import com.solid.solidbackend.repositories.apprepository.*;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamActivityRepository teamActivityRepository;
    private final TeamMembershipRepository teamMembershipRepository;
    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamActivityRepository teamActivityRepository, TeamMembershipRepository teamMembershipRepository, UserRepository userRepository, AssessmentRepository assessmentRepository, ActivityRepository activityRepository) {
        this.teamRepository = teamRepository;
        this.teamActivityRepository = teamActivityRepository;
        this.teamMembershipRepository = teamMembershipRepository;
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;

        this.activityRepository = activityRepository;
    }

    public Team getTeamByName(String name) {
        Optional<Team> teamOptional = teamRepository.findByName(name);

        if (!teamOptional.isPresent())
            throw new TeamNotFoundException(name);

        return teamOptional.get();
    }

    public Team createTeam(String teamName, User teamLeader)
    {
        Optional<Team> teamOptional = teamRepository.findByName(teamName);

        if(teamOptional.isPresent())
            throw new TeamExistsException(teamName);

        Team team = new Team(teamName, teamLeader);
        return teamRepository.save(team);
    }

    public TeamDetails getTeamDetailsFromAnActivity(String activityName, String teamname) {
        List<User> members = teamMembershipRepository.findAllUsersByTeamName(teamname);

        // for each member, i get all assessments. From there i extract all grades and attendances.
        List<Float> gradesOfMembers = new LinkedList<>();
        List<Integer> attendancesOfMembers = new LinkedList<>();
        for (var member :
                members) {
            List<Assessment> memberAssessments = assessmentRepository.findAllByUserId(member.getId());

            float meanOfAllGrades = 0f;
            int attendanceCount = 0;
            for (var ass :
                    memberAssessments) {
                meanOfAllGrades += ass.getGrade();
                if (ass.isAttended())
                    attendanceCount++;
            }
            if (memberAssessments.size() != 0) // check for division by 0
                meanOfAllGrades /= memberAssessments.size();

            gradesOfMembers.add((Float) meanOfAllGrades);
            attendancesOfMembers.add((Integer) attendanceCount);
        }

        Float teamGrade = 0f;
        for (var memberGrade :
                gradesOfMembers) {
            teamGrade += memberGrade;
        }
        if (teamGrade != 0) // check for division by 0
            teamGrade /= gradesOfMembers.size();

        return new TeamDetails(members, gradesOfMembers, attendancesOfMembers, teamGrade, members.size());
    }

    @Override
    public List<Team> getTeamsByActivity(String username, String activityName) {

        Activity selectedActivity = activityRepository.findActivityByName(activityName).orElseThrow(
                () -> new NoActivityFoundException(activityName)
        );
        List<TeamActivity> teamActivities = teamActivityRepository.findByActivity_Id(selectedActivity.getId());
        return teamActivities.stream().map(TeamActivity::getTeam).collect(Collectors.toList());

    }





    @Override
    public User addUserToTeam(String username, String teamName) {
        // Find the Team object by name
        Team joinedTeam = teamRepository.findByName(teamName).orElseThrow(
                () -> new TeamNotFoundException("Team with name '" + teamName + "' not found.")
        );
        // Check if user is already part of the team
        Optional<TeamMembership> teamMembership = teamMembershipRepository.findByTeamAndUser(joinedTeam.getId(), username);
        if (teamMembership.isPresent()) {
            throw new TeamMembershipExistsException("User with username '" + username + "' already joined the team '" + teamName + "'.");
        }
        // Take the whole user object
        User newUser = userRepository.findByName(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
        // Create a new TeamMembership
        TeamMembership tm = new TeamMembership(joinedTeam, newUser);
        teamMembershipRepository.save(tm);

        return newUser;
    }

}

package com.solid.solidbackend.services.implementations;

import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.exceptions.TeamExistsException;
import com.solid.solidbackend.exceptions.TeamMembershipExistsException;
import com.solid.solidbackend.exceptions.TeamNotFoundException;
import com.solid.solidbackend.repositories.apprepository.*;
import com.solid.solidbackend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
            throw new TeamNotFoundException(String.format("Team %s not found when searched by name.", name));

        return teamOptional.get();
    }

    public Team createTeam(String teamName, User teamLeader)
    {
        Optional<Team> teamOptional = teamRepository.findByName(teamName);

        if(teamOptional.isPresent())
            throw new TeamExistsException("Team already exists on creating.");

        Team team = new Team(teamName, teamLeader);
        teamRepository.save(team);
        return team;
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

        return new TeamDetails(members, gradesOfMembers, attendancesOfMembers, teamGrade);
    }

    @Override
    public List<Team> getTeamsByActivity(String activityName) {
        List<TeamActivity> teamActivities = teamActivityRepository.findAllTeamsByActivityName(activityName);
        return teamActivities.stream().map(TeamActivity::getTeam).toList();
    }

    public User addUserToTeam(String username, String teamName) {
        // Find the Team object by name
        var teamOptional = teamRepository.findByName(teamName);
        Team team = teamOptional.get();

        // If exists, throw successfull exception
        var teamMembershipOptional = teamMembershipRepository.findById(team.getId());

        if (teamMembershipOptional.isPresent()) {
            throw new TeamMembershipExistsException("User already joined the team.");
        }
        // else it doesn't exist, create new TeamMemebership


        var userOptional = userRepository.findByName(username);
        User user = userOptional.get();
        // from this point i suppose the user already exists.

        TeamMembership tm = new TeamMembership(team, user);
        teamMembershipRepository.save(tm);

        return user;
    }

}

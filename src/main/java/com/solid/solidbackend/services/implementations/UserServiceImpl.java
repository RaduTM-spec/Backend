package com.solid.solidbackend.services.implementations;


import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.RoleNotAllowedException;
import com.solid.solidbackend.exceptions.UserCreationException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
import com.solid.solidbackend.repositories.apprepository.*;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TeamService teamService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TeamService teamService) {
        this.userRepository = userRepository;
        this.teamService = teamService;
    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }

    @Override
    public User saveUser(User user) {

        // we check if the user already exists by name
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserCreationException(user.getName());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User createNewUser(String name, Role role) {

        String pictureUrl = "https://robohash.org/" + name + ".png";
        if (userRepository.findByName(name).isPresent()) {
            throw new RuntimeException("User with username `" + name +"` already exists!");
        }
        User newUser = new User(name, role, pictureUrl);
        return saveUser(newUser);
    }

    @Override
    @Transactional
    public User createAndAddLeadToTeam(String username, String teamName) {
        User newLead = createNewUser(username, Role.TEAM_LEADER);
        teamService.createTeam(teamName, newLead);
        return teamService.addUserToTeam(username, teamName);
    }

    @Override
    @Transactional
    public User createAndAddMemberToTeam(String username, String teamName) {
        User newMember = createNewUser(username, Role.MEMBER);
        return teamService.addUserToTeam(newMember.getName(), teamName);
    }

    @Override
    public void checkIfUserIsMentor(String userName) {
        User mentor = getUserByName(userName);
        if (mentor.getRole() != Role.MENTOR) throw new RoleNotAllowedException("MENTORS");
    }

    @Override
    public void checkIfUserIsLead(String userName) {
        User mentor = getUserByName(userName);
        if (mentor.getRole() != Role.MENTOR) throw new RoleNotAllowedException("LEADS");
    }

    @Override
    public void checkIfUserIsMentorOrLead(String userName) {
        User mentor = getUserByName(userName);
        if (mentor.getRole() != Role.MENTOR) throw new RoleNotAllowedException("MENTORS and LEADS");
    }

    @Override
    public Role checkUserRole(User user) {

        return switch (user.getRole()) {
            case MEMBER -> Role.MEMBER;
            case TEAM_LEADER -> Role.TEAM_LEADER;
            case MENTOR -> Role.MENTOR;
        };
    }



}
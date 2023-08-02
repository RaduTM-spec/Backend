package com.solid.solidbackend.services.implementations;


import com.solid.solidbackend.entities.*;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.RoleNotAllowedException;
import com.solid.solidbackend.exceptions.UserExistsException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
import com.solid.solidbackend.repositories.apprepository.*;
import com.solid.solidbackend.services.TeamService;
import com.solid.solidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByName(String name) throws UserNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }

    @Override
    public User saveUser(User user) throws UserExistsException{

        // we check if the user already exists by name
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new UserExistsException("User " + user.getName() + " already exists in the database!");
        }

        return userRepository.save(user);
    }


    @Override

    public User createUser(String name, Role role) throws UserExistsException{
        if (userRepository.findByName(name).isPresent()) {
            throw new UserExistsException("User with username `" + name +"` already exists!");
        }

        String pictureUrl = "https://robohash.org/" + name + ".png";
        User newUser = new User(name, role, pictureUrl);
        return saveUser(newUser);
    }


    @Override
    public void checkIfUserIsMentor(String userName) throws RoleNotAllowedException{
        User mentor = findUserByName(userName);
        if (mentor.getRole() != Role.MENTOR) throw new RoleNotAllowedException("MENTORS");
    }

    @Override
    public void checkIfUserIsLeader(String userName) throws RoleNotAllowedException{
        User user = findUserByName(userName);
        if (user.getRole() != Role.TEAM_LEADER) throw new RoleNotAllowedException("TEAM LEADERS");
    }
}
package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUserByName(String name);

    User saveUser(User user);

    User createNewUser(String name, Role role);

    void checkIfUserIsMentor(String userName);

    void checkIfUserIsLead(String userName);

    void checkIfUserIsMentorOrLead(String userName);

    Role checkUserRole(User user);
}

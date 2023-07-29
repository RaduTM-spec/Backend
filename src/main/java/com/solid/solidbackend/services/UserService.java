package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import com.solid.solidbackend.exceptions.RoleNotAllowedException;
import com.solid.solidbackend.exceptions.UserExistsException;
import com.solid.solidbackend.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByName(String name) throws UserNotFoundException;

    User saveUser(User user) throws UserExistsException;

    User createUser(String name, Role role) throws UserExistsException;

    void checkIfUserIsMentor(String userName) throws RoleNotAllowedException;

    void checkIfUserIsLeader(String userName) throws RoleNotAllowedException;
}

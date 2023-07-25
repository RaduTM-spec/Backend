package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.Assessment;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.enums.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User getUserByName(String name);

    User getUserByNameAndRole(String name, String role);

    User saveUser(User user);

    User createNewUser(String name, Role role);

    User createAndAddLeadToTeam(String username, String teamName);

    User createAndAddMemberToTeam(String username, String teamName);
}

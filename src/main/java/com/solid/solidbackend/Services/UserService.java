package com.solid.solidbackend.Services;

import com.solid.solidbackend.Entities.Assessment;
import com.solid.solidbackend.Entities.User;
import com.solid.solidbackend.Repositories.AssessmentRepository;
import com.solid.solidbackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
}

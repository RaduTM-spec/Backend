package com.solid.solidbackend.Services;

import com.solid.solidbackend.Entities.User;
import com.solid.solidbackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        return null;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

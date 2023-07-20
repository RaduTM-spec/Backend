package com.solid.solidbackend.services;

import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findUserByName(String name);

    User saveUser(User user);
}

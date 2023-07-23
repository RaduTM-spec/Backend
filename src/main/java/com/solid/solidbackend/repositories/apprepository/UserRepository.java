package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.User;
import com.solid.solidbackend.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByNameAndRole(String name, Role role);

}

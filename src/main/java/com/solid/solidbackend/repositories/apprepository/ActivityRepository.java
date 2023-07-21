package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Team, Long> {
}

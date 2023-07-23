package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}

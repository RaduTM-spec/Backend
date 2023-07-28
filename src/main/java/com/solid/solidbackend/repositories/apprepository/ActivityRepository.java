package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findActivityByName(String activity);

    Collection<Activity> findActivitiesByCreator(User user);

    @Query("SELECT DISTINCT a FROM Activity a LEFT JOIN MentorActivity ma ON a.id = ma.activity.id WHERE a.creator = :user OR ma.mentor = :user")
    List<Activity> findActivitiesByUser(User user);
}

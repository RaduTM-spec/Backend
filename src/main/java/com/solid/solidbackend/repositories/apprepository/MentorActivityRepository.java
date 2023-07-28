package com.solid.solidbackend.repositories.apprepository;

import com.solid.solidbackend.entities.Activity;
import com.solid.solidbackend.entities.MentorActivity;
import com.solid.solidbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MentorActivityRepository extends JpaRepository<MentorActivity, Long> {

    @Query("SELECT ma.activity FROM MentorActivity ma WHERE ma.mentor = :user")
    List<Activity> findActivitiesByMentor(User user);
}

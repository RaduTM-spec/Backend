package com.solid.solidbackend.Repositories;

import com.solid.solidbackend.Entities.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    @Query("SELECT * FROM assessments WHERE userId == ?1")
    public Optional<List<Assessment>> findAllByUserId(Long userId);

}

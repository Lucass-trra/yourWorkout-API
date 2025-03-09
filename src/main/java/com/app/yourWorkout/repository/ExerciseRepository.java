package com.app.yourWorkout.repository;

import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.entities.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    //READ
    Optional<Exercise> findByName(String name);

    //DELETE
    void deleteByName(String name);

    boolean existsByName(String name);
}

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
    //READ GENERAL
    Set<Exercise> findByName(String name);

    // READ BY WORKOUT ID
    Optional<Exercise> findByWorkoutAndName(Workout workout, String name);
    Page<Exercise> findAllByWorkout(Workout workout, Pageable pageable);
}

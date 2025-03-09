package com.app.yourWorkout.repository;

import com.app.yourWorkout.entities.User;
import com.app.yourWorkout.entities.Workout;
import com.app.yourWorkout.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    //READ GENERAL
    Set<Workout> findByName(String name);

    // READ BY USER ID
    Optional<Workout> findByUserAndName(User user, String name);

    Optional<Workout> findByUserAndWorkoutId(User user, int workoutId);

    Page<Workout> findAllByUser(User user, Pageable pageable);

    //DELETE
    void deleteByUserAndWorkoutId(User user, int workoutId);

    boolean existsByUserAndName(User user, String name);

    boolean existsByUserAndWorkoutId(User user, int workoutId);

}

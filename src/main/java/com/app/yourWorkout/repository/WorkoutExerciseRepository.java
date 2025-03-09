package com.app.yourWorkout.repository;

import com.app.yourWorkout.entities.WorkoutExercise;
import com.app.yourWorkout.associations.WorkoutExerciseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, WorkoutExerciseId> {
}


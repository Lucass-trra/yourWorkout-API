package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.service.ExerciseService;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@ReflectiveScan
@RequestMapping("api/exercises")
public class ExerciseDeleteController {
    private final ExerciseService exerciseService;

    public ExerciseDeleteController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    //DELETE BY WORKOUT ID
    @DeleteMapping("workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<Void> deleteByWorkoutId(@PathVariable int workoutId,
                                                  @PathVariable int exerciseId)
    {
        exerciseService.deleteByWorkoutId(exerciseId, workoutId);
        return ResponseEntity.noContent().build();
    }
}

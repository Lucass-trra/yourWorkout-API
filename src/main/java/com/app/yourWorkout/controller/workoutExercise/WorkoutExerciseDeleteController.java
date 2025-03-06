package com.app.yourWorkout.controller.workoutExercise;

import com.app.yourWorkout.service.WorkoutExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WorkoutExerciseDeleteController {
    private final WorkoutExerciseService workoutExerciseService;

    @DeleteMapping("api/workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<Void> deleteByWorkoutAndExercise(@PathVariable int workoutId,
                                                           @PathVariable int exerciseId)
    {
        workoutExerciseService.deleteByWorkoutAndExercise(workoutId, exerciseId);
        return ResponseEntity.noContent().build();
    }
}

package com.app.yourWorkout.controller.workoutExercise;

import com.app.yourWorkout.DTO.request.exercise.WorkoutExerciseRequest;
import com.app.yourWorkout.DTO.response.WorkoutExerciseReadResponse;
import com.app.yourWorkout.service.WorkoutExerciseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WorkoutExerciseCreateController {
    private final WorkoutExerciseService workoutExerciseService;

    @PostMapping("api/workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<WorkoutExerciseReadResponse> saveWorkoutExercise(
            @PathVariable int workoutId,
            @PathVariable int exerciseId,
            @RequestBody @Valid WorkoutExerciseRequest workoutExerciseRequest)
    {
        return ResponseEntity.ok(
                workoutExerciseService.saveWorkoutExercise(workoutId, exerciseId, workoutExerciseRequest)
        );
    }
}

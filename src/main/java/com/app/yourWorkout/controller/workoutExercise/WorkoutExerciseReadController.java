package com.app.yourWorkout.controller.workoutExercise;


import com.app.yourWorkout.DTO.response.WorkoutExerciseReadResponse;
import com.app.yourWorkout.service.WorkoutExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WorkoutExerciseReadController {
    private final WorkoutExerciseService workoutExerciseService;

    @GetMapping("api/workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<WorkoutExerciseReadResponse> findByWorkoutAndExercise(@PathVariable int workoutId,
                                                                                @PathVariable int exerciseId)
    {
        return ResponseEntity.ok(workoutExerciseService.findByWorkoutAndExercise(workoutId, exerciseId));
    }

}

package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseUpdateRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exercises")
public class ExerciseUpdateController {
    private final ExerciseService exerciseService;

    public ExerciseUpdateController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    //UPDATE BY WORKOUT ID
    @PutMapping("workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<ExerciseReadResponse> updateByWorkoutId(@PathVariable int workoutId,
                                                                  @PathVariable int exerciseId,
                                                                  @RequestBody ExerciseUpdateRequest exerciseRequest)
    {
        return ResponseEntity.ok(exerciseService.updateByWorkoutId(workoutId, exerciseId, exerciseRequest));
    }
}

package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseCreateRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exercises")
public class ExerciseCreateController {
    private final ExerciseService exerciseService;

    public ExerciseCreateController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    //CREATE BY WORKOUT ID
    @PostMapping("workout/{workoutId}/exercise")
    public ResponseEntity<ExerciseReadResponse> saveByWorkoutId(@PathVariable int workoutId,
                                                                @RequestBody ExerciseCreateRequest exerciseRequest)
    {
        return ResponseEntity.ok(exerciseService.saveByWorkoutId(workoutId, exerciseRequest));
    }
}

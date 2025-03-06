package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ExerciseCreateController {
    private final ExerciseService exerciseService;

    public ExerciseCreateController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    //CREATE BY WORKOUT ID
    @PostMapping("api/exercises")
    public ResponseEntity<Exercise> saveExercise(@Valid @RequestBody ExerciseRequest exerciseRequest)
    {
        return ResponseEntity.ok(exerciseService.saveExercise(exerciseRequest));
    }
}

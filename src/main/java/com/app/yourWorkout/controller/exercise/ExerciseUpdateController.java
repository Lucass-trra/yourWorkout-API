package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/exercises")
public class ExerciseUpdateController {
    private final ExerciseService exerciseService;

    public ExerciseUpdateController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    //UPDATE BY WORKOUT ID
    @PutMapping("id/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable int exerciseId,
                                                   @Valid @RequestBody ExerciseRequest exerciseRequest)
    {
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseId, exerciseRequest));
    }
}

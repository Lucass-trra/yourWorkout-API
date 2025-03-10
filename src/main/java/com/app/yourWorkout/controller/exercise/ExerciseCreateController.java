package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class ExerciseCreateController {
    private final ExerciseService exerciseService;

    //CREATE BY WORKOUT ID
    @PostMapping("api/exercises")
    public ResponseEntity<ExerciseReadResponse> saveExercise(@Valid @RequestBody ExerciseRequest exerciseRequest)
    {
        return ResponseEntity.ok(exerciseService.saveExercise(exerciseRequest));
    }
}

package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class ExerciseUpdateController {
    private final ExerciseService exerciseService;

    //UPDATE BY WORKOUT ID
    @PutMapping("api/exercises/id/{id}")
    public ResponseEntity<ExerciseReadResponse> updateExercise(@PathVariable int exerciseId,
                                                               @Valid @RequestBody ExerciseRequest exerciseRequest)
    {
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseId, exerciseRequest));
    }
}

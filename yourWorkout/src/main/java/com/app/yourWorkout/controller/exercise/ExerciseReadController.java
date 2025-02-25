package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.service.ExerciseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/exercises")
public class ExerciseReadController {
    private final ExerciseService exerciseService;

    public ExerciseReadController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    //READ BY WORKOUT ID
    @GetMapping("workout/{workoutId}/exercise/{name}")
    public ResponseEntity<ExerciseReadResponse> findByWorkoutIdAndName(@PathVariable int workoutId,
                                                                       @PathVariable String name)
    {
        return ResponseEntity.ok(exerciseService.findByWorkoutIdAndName(workoutId, name));
    }

    @GetMapping("workout/{workoutId}/exercises")
    public ResponseEntity<Page<ExerciseReadResponse>> findAllByWorkoutId(@PathVariable int workoutId,
                                                                         Pageable pageable)
    {
        return ResponseEntity.ok(exerciseService.findAllByWorkoutId(workoutId, pageable));
    }
}

package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.service.ExerciseService;
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
    @GetMapping("id/{id}")
    public ResponseEntity<Exercise> findById(@PathVariable int exerciseId) {
        return ResponseEntity.ok(exerciseService.findById(exerciseId));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Exercise> findByName(@PathVariable String name) {
        return ResponseEntity.ok(exerciseService.findByName(name));
    }
}

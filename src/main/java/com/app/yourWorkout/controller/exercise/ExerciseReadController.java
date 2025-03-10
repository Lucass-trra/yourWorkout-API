package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/exercises")
@AllArgsConstructor
public class ExerciseReadController {
    private final ExerciseService exerciseService;

    //READ BY WORKOUT ID
    @GetMapping("id/{id}")
    public ResponseEntity<ExerciseReadResponse> findById(@PathVariable int exerciseId) {
        return ResponseEntity.ok(exerciseService.findById(exerciseId));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<ExerciseReadResponse> findByName(@PathVariable String name) {
        return ResponseEntity.ok(exerciseService.findByName(name));
    }
}

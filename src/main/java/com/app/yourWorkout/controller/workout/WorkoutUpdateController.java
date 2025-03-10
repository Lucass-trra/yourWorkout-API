package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutUpdateRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class WorkoutUpdateController {
    private final WorkoutService workoutService;

    //UPDATE BY USER
    @PutMapping("api/workouts/user/{userId}/workout/{workoutId}")
    public ResponseEntity<WorkoutReadResponse> updateByUserId(@PathVariable int userId,
                                                              @PathVariable int workoutId,
                                                              @Valid @RequestBody WorkoutUpdateRequest workoutRequest)
    {
        return ResponseEntity.ok(workoutService.updateByUserId(userId,workoutId ,workoutRequest));
    }
}

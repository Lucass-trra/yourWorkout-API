package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutCreateRequest;
import com.app.yourWorkout.DTO.request.workout.WorkoutUpdateRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/workouts")
public class WorkoutUpdateController {
    private final WorkoutService workoutService;

    public WorkoutUpdateController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    //UPDATE BY USER
    @PutMapping("user/{userId}/workout/{workoutId}")
    public ResponseEntity<WorkoutReadResponse> updateByUserId(@PathVariable int userId,
                                                              @PathVariable int workoutId,
                                                              @Valid @RequestBody WorkoutUpdateRequest workoutRequest)
    {
        return ResponseEntity.ok(workoutService.updateByUserId(userId,workoutId ,workoutRequest));
    }

}

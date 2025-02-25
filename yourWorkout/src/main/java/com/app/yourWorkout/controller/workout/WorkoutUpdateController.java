package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
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
                                                              @RequestBody WorkoutRequest workoutRequest)
    {
        return ResponseEntity.ok(workoutService.updateByUserId(userId,workoutId ,workoutRequest));
    }

}

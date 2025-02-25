package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/workouts")
public class WorkoutCreateController {
    private final WorkoutService workoutService;

    public WorkoutCreateController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    //CREATE BY USER
    @PostMapping("user/{userId}")
    public ResponseEntity<WorkoutReadResponse> saveByUserId(@PathVariable int userId,
                                                            @RequestBody WorkoutRequest workoutRequest)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workoutService.saveByUserId(userId, workoutRequest));
    }
}

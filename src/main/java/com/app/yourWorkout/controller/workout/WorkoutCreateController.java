package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutCreateRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class WorkoutCreateController {
    private final WorkoutService workoutService;

    //CREATE BY USER
    @PostMapping("api/workouts/user/{userId}")
    public ResponseEntity<WorkoutReadResponse> saveByUserId(@PathVariable int userId,
                                                            @Valid @RequestBody WorkoutCreateRequest workoutRequest)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workoutService.saveByUserId(userId, workoutRequest));
    }
}

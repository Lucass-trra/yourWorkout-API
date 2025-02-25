package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/workouts")
public class WorkoutDeleteController {
    private final WorkoutService workoutService;

    public WorkoutDeleteController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    //DELETE BY USER
    @DeleteMapping("user/{userId}/workout/{workoutId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable int userId,
                                               @PathVariable int workoutId)
    {
        workoutService.deleteByUserId(userId, workoutId);
        return ResponseEntity.noContent().build();
    }


}

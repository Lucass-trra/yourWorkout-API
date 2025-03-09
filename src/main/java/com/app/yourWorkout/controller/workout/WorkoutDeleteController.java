package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WorkoutDeleteController {
    private final WorkoutService workoutService;

    //DELETE BY USER
    @DeleteMapping("api/workouts/user/{userId}/workout/{workoutId}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable int userId,
                                               @PathVariable int workoutId)
    {
        workoutService.deleteByUserId(userId, workoutId);
        return ResponseEntity.noContent().build();
    }


}

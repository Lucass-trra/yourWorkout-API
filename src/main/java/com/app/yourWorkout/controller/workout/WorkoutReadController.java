package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/workouts")
public class WorkoutReadController {
    private final WorkoutService workoutService;

    public WorkoutReadController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    //READ BY USER
    @GetMapping("user/{userId}/workout/{name}")
    public ResponseEntity<WorkoutReadResponse> findByUserIdAndName(@PathVariable int userId,
                                                                   @PathVariable String name)
    {
        return ResponseEntity.ok(workoutService.findByUserIdAndName(userId, name));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Page<WorkoutReadResponse>> findAllByUserId(@PathVariable int userId, Pageable pageable)
    {
        return ResponseEntity.ok(workoutService.findAllByUserId(userId, pageable));
    }


}

package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.workout.WorkoutRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkoutService {
    //READ GENERAL
    WorkoutReadResponse findById(int workoutId);

    //READ BY USER
    WorkoutReadResponse findByUserIdAndName(int userId, String name);
    Page<WorkoutReadResponse> findAllByUserId(int userId, Pageable pageable);

    //DELETE BY USER
    void deleteByUserId(int userId, int workoutId);
    
    //CREATE BY USER
    WorkoutReadResponse saveByUserId(int userId, WorkoutRequest workoutRequest);

    //UPDATE BY USER
    WorkoutReadResponse updateByUserId(int userId, int workoutId, WorkoutRequest workoutRequest);
}

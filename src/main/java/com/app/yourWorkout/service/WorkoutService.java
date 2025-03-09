package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.workout.WorkoutCreateRequest;
import com.app.yourWorkout.DTO.request.workout.WorkoutUpdateRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkoutService {
    //READ GENERAL
    WorkoutReadResponse findById(int id);

    //READ BY USER
    WorkoutReadResponse findByUserIdAndName(int userId, String name);
    Page<WorkoutReadResponse> findAllByUserId(int userId, Pageable pageable);

    //DELETE BY USER
    void deleteByUserId(int userId, int workoutId);
    
    //CREATE BY USER
    WorkoutReadResponse saveByUserId(int userId, WorkoutCreateRequest workoutRequest);

    //UPDATE BY USER
    WorkoutReadResponse updateByUserId(int userId, int workoutId, WorkoutUpdateRequest workoutRequest);
}

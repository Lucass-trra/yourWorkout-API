package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.workout.WorkoutRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.service.WorkoutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
class WorkoutServiceImpl implements WorkoutService {
    //READ GENERAL
    @Override
    public WorkoutReadResponse findById(int workoutId) {
        return null;
    }

    //READ BY USER
    @Override
    public WorkoutReadResponse findByUserIdAndName(int userId, String name) {
        return null;
    }

    @Override
    public Page<WorkoutReadResponse> findAllByUserId(int userId, Pageable pageable) {
        return null;
    }

    //DELETE BY USER
    @Override
    public void deleteByUserId(int userId, int workoutId) {

    }

    //CREATE BY USER
    @Override
    public WorkoutReadResponse saveByUserId(int userId, WorkoutRequest workoutRequest) {
        return null;
    }

    //UPDATE BY USER
    @Override
    public WorkoutReadResponse updateByUserId(int userId, int workoutId, WorkoutRequest workoutRequest) {
        return null;
    }
}

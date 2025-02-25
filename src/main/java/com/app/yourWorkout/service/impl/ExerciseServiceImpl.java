package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.exercise.ExerciseCreateRequest;
import com.app.yourWorkout.DTO.request.exercise.ExerciseUpdateRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.service.ExerciseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
class ExerciseServiceImpl implements ExerciseService {
    //READ GENERAL
    @Override
    public Exercise findById(int exerciseId) {
        return null;
    }

    //READ BY WORKOUT ID
    @Override
    public ExerciseReadResponse findByWorkoutIdAndName(int workoutId, String name) {
        return null;
    }

    @Override
    public Page<ExerciseReadResponse> findAllByWorkoutId(int workoutId, Pageable pageable) {
        return null;
    }

    //DELETE BY WORKOUT ID
    @Override
    public void deleteByWorkoutId(int exerciseId, int workoutId) {

    }

    //SAVE BY WORKOUT ID
    @Override
    public ExerciseReadResponse saveByWorkoutId(int workoutId, ExerciseCreateRequest exerciseRequest) {
        return null;
    }

    //UPDATE BY WORKOUT ID
    @Override
    public ExerciseReadResponse updateByWorkoutId(int workoutId,
                                                  int exerciseId,
                                                  ExerciseUpdateRequest exerciseRequest)
    {
        return null;
    }
}

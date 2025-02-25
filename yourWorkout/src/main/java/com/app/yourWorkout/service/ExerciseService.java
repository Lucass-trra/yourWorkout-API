package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.exercise.ExerciseCreateRequest;
import com.app.yourWorkout.DTO.request.exercise.ExerciseUpdateRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.Exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseService {
    //READ GENERAL
    Exercise findById(int exerciseId);

    //READ BY WORKOUT ID
    ExerciseReadResponse findByWorkoutIdAndName(int workoutId, String name);
    Page<ExerciseReadResponse> findAllByWorkoutId(int workoutId, Pageable pageable);

    //DELETE BY WORKOUT ID
    void deleteByWorkoutId(int exerciseId, int workoutId);

    //CREATE BY WORKOUT ID
    ExerciseReadResponse saveByWorkoutId(int workoutId, ExerciseCreateRequest exerciseRequest);

    //UPDATE BY WORKOUT ID
    ExerciseReadResponse updateByWorkoutId(int workoutId,
                                           int exerciseId,
                                           ExerciseUpdateRequest exerciseRequest);
}

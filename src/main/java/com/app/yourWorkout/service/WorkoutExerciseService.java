package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.exercise.WorkoutExerciseRequest;
import com.app.yourWorkout.DTO.response.WorkoutExerciseReadResponse;


    public interface WorkoutExerciseService {
        //READ
        WorkoutExerciseReadResponse findByWorkoutAndExercise(int workoutId, int exerciseId);

        //DELETE
        void deleteByWorkoutAndExercise(int workoutId, int exerciseId);

        //CREATE
        WorkoutExerciseReadResponse saveWorkoutExercise(int workoutId,
                                                        int exerciseId,
                                                        WorkoutExerciseRequest exerciseRequest);

        //UPDATE
        WorkoutExerciseReadResponse updateWorkoutExercise(int workoutId,
                                                          int exerciseId,
                                                          WorkoutExerciseRequest exerciseRequest);
    }

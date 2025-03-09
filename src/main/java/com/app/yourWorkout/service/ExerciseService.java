package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.Exercise;

public interface ExerciseService {
    //READ
    ExerciseReadResponse findById(int id);

    ExerciseReadResponse findByName(String name);

    //DELETE
    void deleteById(int id);

    void deleteByName(String name);

    //CREATE
    ExerciseReadResponse saveExercise(ExerciseRequest exerciseRequest);

    //UPDATE
    ExerciseReadResponse updateExercise(int exerciseId, ExerciseRequest exerciseRequest);
}

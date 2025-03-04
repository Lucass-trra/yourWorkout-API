package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.entities.Exercise;

public interface ExerciseService {
    //READ
    Exercise findById(int id);

    Exercise findByName(String name);

    //DELETE
    void deleteById(int id);

    void deleteByName(String name);

    //CREATE
    Exercise saveExercise(ExerciseRequest exerciseRequest);

    //UPDATE
    Exercise updateExercise(int exerciseId, ExerciseRequest exerciseRequest);
}

package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.entities.WorkoutExercise;

import java.time.LocalTime;

public record WorkoutExerciseReadResponse(
        ExerciseReadResponse exercise,
        Short sets,
        Short repeats,
        Short weight,
        LocalTime restBetweenSets
) {
    public static WorkoutExerciseReadResponse from(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseReadResponse(
                ExerciseReadResponse.from(workoutExercise.getExercise()),
                workoutExercise.getSets(),
                workoutExercise.getRepeats(),
                workoutExercise.getWeight(),
                workoutExercise.getRestBetweenSets()
        );
    }
}

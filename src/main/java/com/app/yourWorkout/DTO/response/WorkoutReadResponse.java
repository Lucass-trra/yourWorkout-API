package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.entities.Workout;

public record WorkoutReadResponse(
        String name,
        boolean isCurrent,
        String description
) {
    public static WorkoutReadResponse from(Workout workout) {
        return new WorkoutReadResponse(
                workout.getName(),
                workout.getIsCurrent(),
                workout.getDescription()
        );
    }
}

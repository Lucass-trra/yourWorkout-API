package com.app.yourWorkout.DTO.request.workout;

public record WorkoutRequest(
        String name,
        boolean isCurrent,
        String description
) {
}

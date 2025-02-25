package com.app.yourWorkout.DTO.response;

public record WorkoutReadResponse(
        String name,
        boolean isCurrent,
        String description
) {
}

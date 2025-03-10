package com.app.yourWorkout.DTO.request.workout;

import jakarta.validation.constraints.Pattern;

public record WorkoutUpdateRequest(
        @Pattern(regexp = "^(?!\\s*$).+", message = "name cannot be empty or blank")
        String name,

        Boolean isCurrent,

        @Pattern(regexp = "^(?!\\s*$).+", message = "Description cannot be empty or blank")
        String description
) {}

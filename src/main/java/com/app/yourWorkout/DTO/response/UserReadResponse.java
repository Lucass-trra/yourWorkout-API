package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.enums.LevelOfExperience;
import com.app.yourWorkout.enums.WorkoutGoal;

import java.time.LocalDate;

public record UserReadResponse(
        String name,
        String email,
        String sex,
        LocalDate datOfbBirth,
        LevelOfExperience levelOfExperience,
        WorkoutGoal workoutGoal,
        short height,
        short weight
) {
}

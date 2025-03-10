package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.entities.User;
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
    public static UserReadResponse from(User user) {
        return new UserReadResponse(
                user.getName(),
                user.getEmail(),
                user.getSex(),
                user.getDateOfBirth(),
                user.getLevelOfExperience(),
                user.getWorkoutGoal(),
                user.getHeight(),
                user.getWeight()
        );
    }
}

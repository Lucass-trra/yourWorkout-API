package com.app.yourWorkout.DTO.request.user;

import com.app.yourWorkout.enums.LevelOfExperience;
import com.app.yourWorkout.enums.WorkoutGoal;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserUpdateRequest(
    String name,
    String email,
    String password,
    String sex,
    LocalDate dateOfBirth,
    LevelOfExperience levelOfExperience,
    WorkoutGoal workoutGoal,
    short height,
    short weight
) {}

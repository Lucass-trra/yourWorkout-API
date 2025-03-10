package com.app.yourWorkout.DTO.request.user;

import com.app.yourWorkout.enums.LevelOfExperience;
import com.app.yourWorkout.enums.WorkoutGoal;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserUpdateRequest(
        @Pattern(regexp = "^(?!\\s*$).+", message = "'name' cannot be empty or blank")
        String name,

        @Pattern(regexp = "^(?!\\s*$).+", message = "'email' cannot be empty or blank")
        String email,

        @Pattern(regexp = "^(?!\\s*$).+", message = "'password' cannot be empty or blank")
        String password,

        @Pattern(regexp = "^(?!\\s*$).+", message = "'sex' cannot be empty or blank")
        String sex,

        @Past(message = "'dateOfBirth' must be a past date.")
        LocalDate dateOfBirth,

        LevelOfExperience levelOfExperience,

        WorkoutGoal workoutGoal,

        @Min(value = 1, message = "'height' must be greater than zero.")
        @Max(value = 280, message = "'height' can not be greater than 280 cm")
        Short height,

        @Min(value = 1, message = "Weight must be greater than zero.")
        @Max(value = 700, message = "weight can not be greater than 700 kg")
        Short weight
) { }

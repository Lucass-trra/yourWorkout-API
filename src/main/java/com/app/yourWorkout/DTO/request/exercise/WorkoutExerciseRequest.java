package com.app.yourWorkout.DTO.request.exercise;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WorkoutExerciseRequest(
        @NotNull(message = "This field 'workoutId' is required and cannot be empty.")
        int workoutId,

        @NotNull(message = "This field 'exerciseId' is required and cannot be empty.")
        int exerciseId,

        @Min(value = 1, message = "Sets must be greater than zero.")
        @Max(value = 500, message = "Sets can not be greater than 100")
        Short sets,

        @Min(value = 1, message = "Repeats must be greater than zero.")
        @Max(value = 500, message = "Repeats can not be greater than 500")
        Short repeats,

        @Min(value = 1, message = "Weight must be greater than zero.")
        @Max(value = 2000, message = "weight can not be greater than 2000")
        Short weight,

        LocalTime restBetweenSets
) {}

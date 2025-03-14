package com.app.yourWorkout.DTO.request.exercise;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WorkoutExerciseRequest(
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

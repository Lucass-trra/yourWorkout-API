package com.app.yourWorkout.DTO.request.workout;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WorkoutCreateRequest(
        @NotBlank(message = "this field 'name' is required and can not be empty.")
        String name,

        @NotNull(message = "this field 'isCurrent' is required and can not be empty.")
        Boolean isCurrent,

        @Pattern(regexp = "^(?!\\s*$).+", message = "Description cannot be empty or blank")
        String description
) {
}

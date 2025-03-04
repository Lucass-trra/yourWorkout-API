package com.app.yourWorkout.DTO.request.exercise;

import com.app.yourWorkout.entities.BodyPart;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExerciseRequest(
        @NotBlank(message = "this field 'name' is required and can not be empty.")
        String name,

        @NotNull(message = "this field 'photo' is required and can not be empty.")
        @Size(min = 1, message = "The 'photo' field cannot be empty.")
        byte[] photo,

        @NotNull(message = "this field 'primaryBodyPart' is required and can not be empty.")
        BodyPart primaryBodyPart,

        @NotBlank(message = "this field 'equipment' is required and can not be empty.")
        String equipment,

        @NotBlank(message = "this field 'name' is required and can not be empty.")
        String target,

        @NotBlank(message = "this field 'name' is required and can not be empty.")
        String instructions
) {}

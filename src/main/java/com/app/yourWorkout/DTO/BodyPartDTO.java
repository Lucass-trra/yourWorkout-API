package com.app.yourWorkout.DTO;

import com.app.yourWorkout.entities.BodyPart;
import jakarta.validation.constraints.NotBlank;

public record BodyPartDTO(
        @NotBlank(message = "the field 'name' is required and can not be empty")
        String name
) {

    public static BodyPartDTO from(BodyPart bodyPart) {
        return new BodyPartDTO(bodyPart.getName());
    }
}

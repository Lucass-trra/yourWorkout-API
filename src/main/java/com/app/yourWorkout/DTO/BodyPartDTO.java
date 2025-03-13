package com.app.yourWorkout.DTO;

import com.app.yourWorkout.entities.BodyPart;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record BodyPartDTO(
        @NotBlank(message = "the field 'name' is required and can not be empty")
        String name
) {

    public static BodyPartDTO from(BodyPart bodyPart) {
        return new BodyPartDTO(bodyPart.getName());
    }

    public static Set<BodyPartDTO> fromSet(Set<BodyPart> bodyParts) {
        return bodyParts.stream()
                .map(BodyPartDTO::from)
                .collect(Collectors.toSet());
    }
}

package com.app.yourWorkout.DTO;

import com.app.yourWorkout.entities.BodyPart;

public record BodyPartDTO(String name) {

    public static BodyPartDTO from(BodyPart bodyPart) {
        return new BodyPartDTO(bodyPart.getName());
    }
}

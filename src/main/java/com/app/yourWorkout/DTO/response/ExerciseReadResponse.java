package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.entities.Exercise;

import java.util.Set;

public record ExerciseReadResponse(
        String name,
        byte[] photo,
        BodyPart primaryBodyPart,
        String equipment,
        String target,
        String instructions,
        Set<BodyPart> secondaryBodyParts
) {
    public static ExerciseReadResponse from(Exercise exercise) {
        return new ExerciseReadResponse(
                exercise.getName(),
                exercise.getPhoto(),
                exercise.getPrimaryBodyPart(),
                exercise.getEquipment(),
                exercise.getTarget(),
                exercise.getInstructions(),
                exercise.getSecondaryBodyParts()
        );
    }
}

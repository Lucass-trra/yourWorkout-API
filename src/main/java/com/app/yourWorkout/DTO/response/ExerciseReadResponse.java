package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.entities.Exercise;

import java.util.Set;

public record ExerciseReadResponse(
        String name,
        byte[] photo,
        BodyPartDTO primaryBodyPart,
        String equipment,
        String target,
        String instructions,
        Set<BodyPartDTO> secondaryBodyParts
) {
    public static ExerciseReadResponse from(Exercise exercise) {
        return new ExerciseReadResponse(
                exercise.getName(),
                exercise.getPhoto(),
                BodyPartDTO.from(exercise.getPrimaryBodyPart()),
                exercise.getEquipment(),
                exercise.getTarget(),
                exercise.getInstructions(),
                BodyPartDTO.fromSet(exercise.getSecondaryBodyParts())
        );
    }
}

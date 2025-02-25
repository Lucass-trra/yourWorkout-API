package com.app.yourWorkout.DTO.response;

import com.app.yourWorkout.entities.BodyPart;

import java.time.LocalTime;
import java.util.Set;

public record ExerciseReadResponse(
        String name,
        byte[] photo,
        BodyPart primaryBodyPart,
        String equipment,
        String target,
        String instructions,
        short sets,
        short repeats,
        short weight,
        LocalTime restBetweenSets,
        Set<BodyPart> secondaryBodyParts
) {
}

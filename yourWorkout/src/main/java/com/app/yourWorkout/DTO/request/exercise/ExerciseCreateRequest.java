package com.app.yourWorkout.DTO.request.exercise;

import com.app.yourWorkout.entities.BodyPart;

public record ExerciseCreateRequest(
    String name,
    byte[] photo,
    BodyPart primaryBodyPart,
    String equipment,
    String target,
    String instructions

) {}

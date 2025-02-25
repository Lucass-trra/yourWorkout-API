package com.app.yourWorkout.DTO.request.exercise;

import java.time.LocalDate;

public record ExerciseUpdateRequest(
   short sets,
   short repeats,
   short weight,
   LocalDate restBetweenSets
) {}

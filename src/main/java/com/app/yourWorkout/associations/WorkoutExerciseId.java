package com.app.yourWorkout.associations;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExerciseId implements Serializable {
    @Column(name = "workout_id")
    private int workoutId;

    @Column(name = "exercise_id")
    private int exerciseId;
}

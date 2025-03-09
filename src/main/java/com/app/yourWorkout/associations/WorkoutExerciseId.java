package com.app.yourWorkout.associations;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class WorkoutExerciseId implements Serializable {
    @Column(name = "workout_id")
    @NonNull
    private Integer workoutId;

    @Column(name = "exercise_id")
    @NonNull
    private Integer exerciseId;
}

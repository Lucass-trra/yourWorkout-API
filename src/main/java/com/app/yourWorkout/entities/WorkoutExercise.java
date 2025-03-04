package com.app.yourWorkout.entities;

import com.app.yourWorkout.associations.WorkoutExerciseId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "workout_exercise")
@Getter
@Setter
public class WorkoutExercise {
    @EmbeddedId
    private WorkoutExerciseId id;

    @ManyToOne
    @MapsId("workoutId")
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false)
    private Short sets;

    @Column(nullable = false)
    private Short repeats;

    @Column(nullable = false)
    private Short weight;

    @Column(name = "rest_between_sets")
    private LocalTime restBetweenSets;
}

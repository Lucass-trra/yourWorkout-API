package com.app.yourWorkout.entities;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private int workoutId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @Column(name = "workout_name", unique = true, nullable = false, length = 100)
    @NonNull
    private String name;

    @Column(name = "iscurrent",nullable = false)
    @NonNull
    private Boolean isCurrent;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<WorkoutExercise> exerciseSet = new HashSet<>();
}

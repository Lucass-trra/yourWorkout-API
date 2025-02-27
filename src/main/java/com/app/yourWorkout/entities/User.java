package com.app.yourWorkout.entities;

import com.app.yourWorkout.enums.LevelOfExperience;
import com.app.yourWorkout.enums.WorkoutGoal;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.CascadeType;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username",unique = true, nullable = false, length = 50)
    @NonNull
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    @NonNull
    private String email;

    @Column(unique = true, nullable = false, length = 16)
    @NonNull
    private String password;

    @Column(length = 20)
    private String sex;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "levelofexperience", length = 20)
    private LevelOfExperience levelOfExperience;

    @Enumerated(EnumType.STRING)
    @Column(name = "workoutgoal", length = 50)
    private WorkoutGoal workoutGoal;

    @Column
    // height in centimeters
    private Short height;

    @Column
    private Short weight;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Workout> workoutSet = new HashSet<>();
}

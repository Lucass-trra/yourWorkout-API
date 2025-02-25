package com.app.yourWorkout.entities;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exercise")
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private int exerciseId;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @Column(name = "exercise_name", unique = true, nullable = false, length = 50)
    private String name;

    @Lob // Para armazenar GIFs ou imagens em bytea
    @Column(nullable = false, columnDefinition = "BYTEA")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "bodypart_id", nullable = false)
    private BodyPart primaryBodyPart; // Relacionamento com o músculo principal

    @Column(nullable = false, length = 100)
    private String equipment;

    @Column(nullable = false, length = 100)
    private String target;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String instructions; // Mapeado como String para JSONB

    @Column
    private Short sets;

    @Column
    private Short repeats;

    @Column
    private Short weight;

    @Column(name = "restbetweensets")
    private LocalTime restBetweenSets;

    // Relacionamento muitos para muitos com os músculos secundários
    @ManyToMany
    @JoinTable(
            name = "exercise_bodypart_secundary",
            joinColumns = @JoinColumn(name = "fk_exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_bodypart_id")
    )
    private Set<BodyPart> secondaryBodyParts = new HashSet<>();
}

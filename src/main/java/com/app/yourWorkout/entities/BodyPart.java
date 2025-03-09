package com.app.yourWorkout.entities;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;


@Entity
@Table(name = "bodypart")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BodyPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bodypart_id")
    private int id;

    @Column(unique = true, name = "bodypart_name", nullable = false, length = 50)
    @NonNull
    private String name;
}

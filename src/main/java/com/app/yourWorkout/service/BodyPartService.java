package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.entities.Exercise;

import java.util.List;

public interface BodyPartService {
    //READ
    BodyPartDTO findById(int id);
    BodyPartDTO findByName(String name);

    //DELETE
    void deleteById(int id);
    void deleteByName(String name);

    //CREATE
    BodyPartDTO saveBodyPart(BodyPartDTO bodyPartDTO);

    Exercise saveSecondaryBodyPartsByExercise(int exerciseId, List<String> names);

    //UPDATE
    BodyPartDTO updateBodyPart(int id, BodyPartDTO bodyPartDTO);
}

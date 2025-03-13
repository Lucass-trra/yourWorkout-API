package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.Exercise;

import java.util.List;
import java.util.Set;

public interface BodyPartService {
    //READ
    BodyPartDTO findById(int id);
    BodyPartDTO findByName(String name);

    //DELETE
    void deleteById(int id);
    void deleteByName(String name);

    //CREATE
    BodyPartDTO saveBodyPart(BodyPartDTO bodyPartDTO);

    Set<BodyPartDTO> saveSecondaryBodyPartsByExercise(int exerciseId, List<String> names);

    //UPDATE
    BodyPartDTO updateBodyPart(int id, BodyPartDTO bodyPartDTO);
}

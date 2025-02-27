package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.BodyPartDTO;

public interface BodyPartService {
    //READ
    BodyPartDTO findById(int id);
    BodyPartDTO findByName(String name);

    //DELETE
    void deleteById(int id);
    void deleteByName(String name);

    //CREATE
    BodyPartDTO saveBodyPart(BodyPartDTO bodyPartDTO);

    //UPDATE
    BodyPartDTO updateBodyPart(int id, BodyPartDTO bodyPartDTO);
}

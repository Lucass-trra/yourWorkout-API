package com.app.yourWorkout.service;

import com.app.yourWorkout.entities.BodyPart;

public interface BodyPartService {
    //READ
    BodyPart findById(int id);
    BodyPart findByName(String name);

    //DELETE
    void deleteById(int id);
    void deleteByName(String name);

    //CREATE
    BodyPart saveBodyPart(BodyPart bodyPart);

    //UPDATE
    BodyPart updateBodyPart(int bodyPartId, BodyPart bodyPart);
}

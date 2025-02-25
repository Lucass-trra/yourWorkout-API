package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.service.BodyPartService;
import org.springframework.stereotype.Service;

@Service
class BodyPartServiceImpl implements BodyPartService {
    @Override
    public BodyPart findById(int id) {
        return null;
    }

    @Override
    public BodyPart findByName(String name) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public BodyPart saveBodyPart(BodyPart bodyPart) {
        return null;
    }

    @Override
    public BodyPart updateBodyPart(int bodyPartId, BodyPart bodyPart) {
        return null;
    }
}

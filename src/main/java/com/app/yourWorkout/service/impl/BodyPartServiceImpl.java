package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.exception.DataAlreadyExistException;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.repository.BodyPartRepository;
import com.app.yourWorkout.service.BodyPartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class BodyPartServiceImpl implements BodyPartService {
    private final BodyPartRepository bodyPartRepository;

    @Override
    public BodyPartDTO findById(int id) {
        var bodyPart = bodyPartRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("the body part with id: " + id + " was not found"));

        return new BodyPartDTO(bodyPart.getName());
    }

    @Override
    public BodyPartDTO findByName(String name) {
        var bodyPart = bodyPartRepository.findByName(name)
                .orElseThrow(()-> new DataNotFoundException("the body part with name: " + name + " was not found"));

        return new BodyPartDTO(bodyPart.getName());
    }

    @Override
    public void deleteById(int id) {
        if(!bodyPartRepository.existsById(id)) {
            throw new DataNotFoundException("the body part with id: " + id + " was not found to delete");
        }
        bodyPartRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        if(!bodyPartRepository.existsByName(name)) {
            throw new DataNotFoundException("the body part with name: " + name + " was not found to delete");
        }
        bodyPartRepository.deleteByName(name);
    }

    @Override
    public BodyPartDTO saveBodyPart(BodyPartDTO bodyPartDTO) {
        if(bodyPartRepository.existsByName(bodyPartDTO.name())) {
            throw new DataAlreadyExistException("the body part with name: " + bodyPartDTO.name() + " already exist to save in database");
        }
        var bodyPartSaved = bodyPartRepository.save(new BodyPart(bodyPartDTO.name()));

        return BodyPartDTO.from(bodyPartSaved);
    }

    @Override
    public BodyPartDTO updateBodyPart(int id, BodyPartDTO bodyPartDTO) {
        var bodyPart = bodyPartRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("the body part with id: " + id + " was not found to update"));

        if(bodyPartDTO.name() != null) bodyPart.setName(bodyPartDTO.name());

        var bodyPartSaved = bodyPartRepository.save(bodyPart);
        return BodyPartDTO.from(bodyPartSaved);
    }
}

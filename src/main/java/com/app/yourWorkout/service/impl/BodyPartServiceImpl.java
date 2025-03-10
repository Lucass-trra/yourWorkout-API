package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.exception.CollectionEmptyException;
import com.app.yourWorkout.exception.DataAlreadyExistException;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.repository.BodyPartRepository;
import com.app.yourWorkout.repository.ExerciseRepository;
import com.app.yourWorkout.service.BodyPartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class BodyPartServiceImpl implements BodyPartService {
    private final BodyPartRepository bodyPartRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public BodyPartDTO findById(int id) {
        return bodyPartRepository.findById(id)
                .map(BodyPartDTO::from)
                .orElseThrow(()-> new DataNotFoundException("the body part: " + id + " was not found"));
    }

    @Override
    public BodyPartDTO findByName(String name) {
        return bodyPartRepository.findByName(name)
                .map(BodyPartDTO::from)
                .orElseThrow(()-> new DataNotFoundException("the body part: " + name + " was not found"));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        bodyPartRepository.findById(id).ifPresentOrElse(
                bodyPartRepository::delete,
                () -> {
                    throw new DataNotFoundException("the body part: " + id + " was not found to delete");
                }
        );
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
       bodyPartRepository.findByName(name).ifPresentOrElse(
               bodyPartRepository::delete,
               () -> {
                   throw new DataNotFoundException("the body part: " + name + " was not found to delete");
               }
       );
    }

    @Override
    @Transactional
    public BodyPartDTO saveBodyPart(BodyPartDTO bodyPartDTO) {
        if(bodyPartRepository.existsByName(bodyPartDTO.name())) {
            throw new DataAlreadyExistException("the body part: " + bodyPartDTO.name() + " already exist to save in database");
        }

        return BodyPartDTO.from(
                bodyPartRepository.save(
                        new BodyPart(bodyPartDTO.name())
                )
        );
    }

    @Override
    @Transactional
    public ExerciseReadResponse saveSecondaryBodyPartsByExercise(int exerciseId, List<String> names) {
        return exerciseRepository.findById(exerciseId)
                .map(exercise -> {
                    if(names.isEmpty())
                        throw new CollectionEmptyException("list of body parts secondary names is empty");

                    var secondaryBodyParts = bodyPartRepository.findAllByNameIn(names);

                    if(secondaryBodyParts.isEmpty())
                        throw new CollectionEmptyException("No valid body parts found for the given names.");

                    exercise.getSecondaryBodyParts().addAll(secondaryBodyParts);

                    return ExerciseReadResponse.from(
                            exerciseRepository.save(exercise)
                    );
                })
                .orElseThrow(() -> new DataNotFoundException("the exercise: " + exerciseId + " was not found"));
    }

    @Override
    @Transactional
    public BodyPartDTO updateBodyPart(int id, BodyPartDTO bodyPartDTO) {
        return bodyPartRepository.findById(id)
                .map(bodyPart -> {
                    if(bodyPartRepository.existsByName(bodyPartDTO.name())) {
                        throw new DuplicateDataException("the body part: " + bodyPartDTO.name() + " already exists, can not update it");
                    }

                    Optional.ofNullable(bodyPartDTO.name()).ifPresent(bodyPart::setName);

                    return BodyPartDTO.from(
                            bodyPartRepository.save(bodyPart)
                    );
                })
                .orElseThrow(()-> new DataNotFoundException("the body part: " + id + " was not found to update"));
    }
}

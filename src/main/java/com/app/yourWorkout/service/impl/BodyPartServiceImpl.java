package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.exception.CollectionEmptyException;
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

/**
 * @author lucasterra
 */
@Service
@AllArgsConstructor
class BodyPartServiceImpl implements BodyPartService {
    private final BodyPartRepository bodyPartRepository;
    private final ExerciseRepository exerciseRepository;

    /**
     * @author lucasterra
     * @param id the id of bodyPart
     * @return BodyPartDTO
     * @throws  DataNotFoundException when bodyPart was not found to get
     */
    @Override
    public BodyPartDTO findById(int id) {
        return bodyPartRepository.findById(id)
                .map(BodyPartDTO::from)
                .orElseThrow(()-> new DataNotFoundException("the body part: " + id + " was not found"));
    }

    /**
     * @author lucasterra
     * @param name the name of bodyPart
     * @return BodyPartDTO
     * @throws  DataNotFoundException if the bodyPart was not found to get
     */
    @Override
    public BodyPartDTO findByName(String name) {
        return bodyPartRepository.findByName(name)
                .map(BodyPartDTO::from)
                .orElseThrow(()-> new DataNotFoundException("the body part: " + name + " was not found"));
    }

    /**
     * @author lucasterra
     * @param id the ID of bodyPart
     * @throws  DataNotFoundException if the bodyPart was not found to delete
     */
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

    /**
     * @author lucasterra
     * @param name the name of bodyPart
     * @throws  DataNotFoundException if the bodyPart was not found to delete
     */
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

    /**
     * @author lucasterra
     * @param bodyPartDTO the DTO request used to save a new bodyPart
     * @return BodyPartDTO
     * @throws DuplicateDataException if the bodyPart already exists
     */
    @Override
    @Transactional
    public BodyPartDTO saveBodyPart(BodyPartDTO bodyPartDTO) {
        if(bodyPartRepository.existsByName(bodyPartDTO.name())) {
            throw new DuplicateDataException("the body part: " + bodyPartDTO.name() + " already exist to save in database");
        }

        return BodyPartDTO.from(
                bodyPartRepository.save(
                        new BodyPart(bodyPartDTO.name())
                )
        );
    }

    /**
     * @author lucasterra
     * @param exerciseId the ID from exercise
     * @param names the names of secondary bodyParts for exercise requested
     * @return ExerciseReadResponse
     * @throws CollectionEmptyException if the secondary bodyParts names from param or the list of bodyParts caught is empty
     * @throws DataNotFoundException if the exercise was not found to get its secondary bodyPart
     */
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

    /**
     * @author lucasterra
     * @param id the ID from BodyPart
     * @param bodyPartDTO the DTO request used to update an existing bodyPart
     * @return BodyPartDTO
     * @throws DataNotFoundException if the bodyPart was not found to update
     * @throws DuplicateDataException if the bodyPart DTO has the same name of one bodyPart that already exists
     */
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

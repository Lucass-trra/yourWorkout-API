package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.repository.ExerciseRepository;
import com.app.yourWorkout.service.ExerciseService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author lucasterra
 */
@Service
@AllArgsConstructor
class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;


    /**
     * @author lucasterra
     * @param exerciseId the id of exercise
     * @return ExerciseReadResponse
     * @throws DataNotFoundException if the exercise was not found to get
     */
    @Override
    @Transactional
    public ExerciseReadResponse findById(int exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .map(ExerciseReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("the exercise: " + exerciseId + " was not found"));
    }

    /**
     * @author lucasterra
     * @param name the name of exercise
     * @return ExerciseReadResponse
     * @throws DataNotFoundException if the exercise was not found to get
     */
    @Override
    public ExerciseReadResponse findByName(String name) {
        return exerciseRepository.findByName(name)
                .map(ExerciseReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("the exercise: " + name + " was not found"));
    }

    /**
     * @author lucasterra
     * @param exerciseId the id of exercise
     * @throws DataNotFoundException if the exercise was not found to delete
     */
    @Override
    @Transactional
    public void deleteById(int exerciseId) {
        exerciseRepository.findById(exerciseId).ifPresentOrElse(
                exerciseRepository::delete,
                () -> {
                    throw new DataNotFoundException("the exercise: " + exerciseId + " was not found to delete");
                }
        );
    }

    /**
     * @author lucasterra
     * @param name the name of exercise
     * @throws DataNotFoundException if the exercise was not found to delete
     */
    @Override
    @Transactional
    public void deleteByName(String name) {
        exerciseRepository.findByName(name).ifPresentOrElse(
                exerciseRepository::delete,
                () -> {
                    throw new DataNotFoundException("the exercise: " + name + " was not found to delete");
                }
        );
    }

    /**
     * @author lucasterra
     * @param exerciseRequest the DTO request to save an exercise
     * @return ExerciseReadResponse
     * @throws DuplicateDataException if the exercise already exists
     */
    @Override
    @Transactional
    public ExerciseReadResponse saveExercise(ExerciseRequest exerciseRequest) {
        if(exerciseRepository.existsByName(exerciseRequest.name())) {
            throw new DuplicateDataException("the exercise: " + exerciseRequest.name() + " already exists in database");
        }

        var newExercise = new Exercise(
                exerciseRequest.name(),
                exerciseRequest.primaryBodyPart(),
                exerciseRequest.equipment(),
                exerciseRequest.target(),
                exerciseRequest.instructions()
        );
        Optional.ofNullable(exerciseRequest.photo()).ifPresent(newExercise::setPhoto);

        return ExerciseReadResponse.from(
                exerciseRepository.save(newExercise)
        );
    }

    /**
     * @author lucasterra
     * @param exerciseId the id of exercise
     * @param exerciseRequest the DTO request to save an exercise
     * @return ExerciseReadResponse
     * @throws DataNotFoundException if exercise was not found to update
     * @throws DuplicateDataException if the exercise from DTO has the same name of one exercise that already exists
     */
    @Override
    @Transactional
    public ExerciseReadResponse updateExercise(int exerciseId, ExerciseRequest exerciseRequest) {
        return exerciseRepository.findById(exerciseId)
                .map(exercise -> {
                    if(exerciseRepository.existsByName(exerciseRequest.name())) {
                        throw new DuplicateDataException("the exercise: " + exerciseRequest.name() + " already exists, can not update it");
                    }
                    exercise.setName(exerciseRequest.name());
                    exercise.setPrimaryBodyPart(exerciseRequest.primaryBodyPart());
                    exercise.setEquipment(exerciseRequest.equipment());
                    exercise.setTarget(exerciseRequest.target());
                    exercise.setInstructions(exerciseRequest.instructions());
                    exercise.setPhoto(exerciseRequest.photo());

                    return ExerciseReadResponse.from(
                            exerciseRepository.save(exercise)
                    );
                })
                .orElseThrow(() -> new DataNotFoundException("the exercise: " + exerciseId + " was not found to update"));
    }
}

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

@Service
@AllArgsConstructor
class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    //READ GENERAL
    @Override
    @Transactional
    public ExerciseReadResponse findById(int exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .map(ExerciseReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("the exercise: " + exerciseId + " was not found"));
    }

    @Override
    @Transactional
    public ExerciseReadResponse findByName(String name) {
        return exerciseRepository.findByName(name)
                .map(ExerciseReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("the exercise: " + name + " was not found"));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        exerciseRepository.findById(id).ifPresentOrElse(
                exerciseRepository::delete,
                () -> {
                    throw new DataNotFoundException("the exercise: " + id + " was not found to delete");
                }
        );
    }

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

    @Override
    @Transactional
    public ExerciseReadResponse updateExercise(int exerciseId, ExerciseRequest exerciseRequest) {
        return exerciseRepository.findById(exerciseId)
                .map(exercise -> {
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

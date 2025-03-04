package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.repository.ExerciseRepository;
import com.app.yourWorkout.service.ExerciseService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    //READ GENERAL
    @Override
    public Exercise findById(int exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new DataNotFoundException("the exercise with ID: " + exerciseId + " was not found"));
    }

    @Override
    public Exercise findByName(String name) {
        return exerciseRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException("the exercise with name: " + name + " was not found"));
    }

    @Override
    public void deleteById(int id) {
        if(!exerciseRepository.existsById(id)) {
            throw new DataNotFoundException("the exercise with id: " + id + " was not found to delete");
        }

        exerciseRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        if(!exerciseRepository.existsByName(name)) {
            throw new DataNotFoundException("the exercise with name: " + name + " was not found to delete");
        }

        exerciseRepository.deleteByName(name);
    }

    @Override
    public Exercise saveExercise(ExerciseRequest exerciseRequest) {
        if(exerciseRepository.existsByName(exerciseRequest.name())) {
            throw new DuplicateDataException("the exercise with name: " + exerciseRequest.name() + " already exists in database");
        }

        var newExercise = new Exercise(
                exerciseRequest.name(),
                exerciseRequest.primaryBodyPart(),
                exerciseRequest.equipment(),
                exerciseRequest.target(),
                exerciseRequest.instructions()
        );

        newExercise.setPhoto(exerciseRequest.photo());
        return exerciseRepository.save(newExercise);
    }

    @Override
    public Exercise updateExercise(int exerciseId, ExerciseRequest exerciseRequest) {
        var exercise = findById(exerciseId);

        exercise.setName(exerciseRequest.name());
        exercise.setPrimaryBodyPart(exerciseRequest.primaryBodyPart());
        exercise.setEquipment(exerciseRequest.equipment());
        exercise.setTarget(exerciseRequest.target());
        exercise.setInstructions(exerciseRequest.instructions());
        exercise.setPhoto(exerciseRequest.photo());

        return exerciseRepository.save(exercise);
    }
}

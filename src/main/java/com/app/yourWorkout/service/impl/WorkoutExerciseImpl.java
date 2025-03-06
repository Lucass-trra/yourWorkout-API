package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.exercise.WorkoutExerciseRequest;
import com.app.yourWorkout.DTO.response.WorkoutExerciseReadResponse;
import com.app.yourWorkout.associations.WorkoutExerciseId;
import com.app.yourWorkout.entities.WorkoutExercise;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.repository.ExerciseRepository;
import com.app.yourWorkout.repository.WorkoutExerciseRepository;
import com.app.yourWorkout.repository.WorkoutRepository;
import com.app.yourWorkout.service.WorkoutExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class WorkoutExerciseImpl implements WorkoutExerciseService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    private WorkoutExercise verifyPossibleNullFields(WorkoutExercise workoutExercise,
                                                     WorkoutExerciseRequest workoutExerciseRequest)
    {
        if (workoutExerciseRequest.sets() != null) workoutExercise.setSets(workoutExerciseRequest.sets());
        if (workoutExerciseRequest.repeats() != null) workoutExercise.setRepeats(workoutExerciseRequest.repeats());
        if (workoutExerciseRequest.weight() != null) workoutExercise.setWeight(workoutExerciseRequest.weight());
        if (workoutExerciseRequest.restBetweenSets() != null) workoutExercise.setRestBetweenSets(workoutExerciseRequest.restBetweenSets());

        return workoutExercise;
    }

    private void verifyEntityExistence(int workoutId, int exerciseId) {
        boolean workoutExists = workoutRepository.existsById(workoutId);
        boolean exerciseExists = exerciseRepository.existsById(exerciseId);

        if (!workoutExists && !exerciseExists) {
            throw new DataNotFoundException("Neither the workout " + workoutId + " nor the exercise " + exerciseId + " were found.");
        }

        if (!workoutExists) {
            throw new DataNotFoundException("The workout " + workoutId + " was not found.");
        }
        if (!exerciseExists) {
            throw new DataNotFoundException("The exercise " + exerciseId + " was not found.");
        }
    }

    @Override
    public WorkoutExerciseReadResponse findByWorkoutAndExercise(int workoutId, int exerciseId) {
        var workoutExerciseId = new WorkoutExerciseId(workoutId, exerciseId);

            return workoutExerciseRepository.findById(workoutExerciseId)
                    .map(WorkoutExerciseReadResponse::from)
                    .orElseThrow(() -> {
                        verifyEntityExistence(workoutId, exerciseId);
                        return new DataNotFoundException("The exercise " + exerciseId + " does not belong to workout " + workoutId + ".");
                    });
    }

    @Override
    @Transactional
    public void deleteByWorkoutAndExercise(int workoutId, int exerciseId) {
        var workoutExerciseId = new WorkoutExerciseId(workoutId, exerciseId);

        workoutExerciseRepository.findById(workoutExerciseId).ifPresentOrElse(
                workoutExerciseRepository::delete,
                () -> {
                    verifyEntityExistence(workoutId, exerciseId);
                    throw new DataNotFoundException("The exercise " + exerciseId + " does not belong to workout " + workoutId + ".");
                }
        );
    }

    @Override
    @Transactional
    public WorkoutExerciseReadResponse saveWorkoutExercise(int workoutId, int exerciseId, WorkoutExerciseRequest workoutExerciseRequest) {
        var workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new DataNotFoundException("The workout: " + workoutId + " was not found"));

        var exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new DataNotFoundException("The exercise: " + exerciseId + " was not found"));

        var workoutExerciseId = new WorkoutExerciseId(workoutId, exerciseId);

        if (workoutExerciseRepository.existsById(workoutExerciseId)) {
            throw new DuplicateDataException("The exercise: " + exerciseId + " already exists for workout: " + workoutId);
        }

        return WorkoutExerciseReadResponse.from(
                workoutExerciseRepository.save(
                        verifyPossibleNullFields(
                                new WorkoutExercise(workoutExerciseId, workout, exercise),
                                workoutExerciseRequest)
                )
        );
    }

    @Override
    @Transactional
    public WorkoutExerciseReadResponse updateWorkoutExercise(int workoutId, int exerciseId, WorkoutExerciseRequest workoutExerciseRequest) {
        var workoutExerciseId = new WorkoutExerciseId(workoutId, exerciseId);

        return workoutExerciseRepository.findById(workoutExerciseId)
                .map(workoutExercise ->
                        WorkoutExerciseReadResponse.from(
                                workoutExerciseRepository.save(
                                        verifyPossibleNullFields(workoutExercise, workoutExerciseRequest)
                                )
                        )
                )
                .orElseThrow(() -> {
                    verifyEntityExistence(workoutId, exerciseId);
                    return new DataNotFoundException("The exercise " + exerciseId + " does not belong to workout " + workoutId + ".");
                });
    }
}


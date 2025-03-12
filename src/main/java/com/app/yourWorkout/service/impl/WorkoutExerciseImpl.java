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


/**
 * @author lucasterra
 */
@Service
@AllArgsConstructor
public class WorkoutExerciseImpl implements WorkoutExerciseService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    /**
     * verify witch fields are not null to compose the workoutExercise object
     *
     * @author lucasterra
     * @param workoutExercise the entity workoutExercise representing association between workout and exercise
     * @param workoutExerciseRequest the DTO request of workoutExercise
     * @return WorkoutExercise
     */
    private WorkoutExercise verifyPossibleNullFields(WorkoutExercise workoutExercise,
                                                     WorkoutExerciseRequest workoutExerciseRequest)
    {
        if (workoutExerciseRequest.sets() != null) workoutExercise.setSets(workoutExerciseRequest.sets());
        if (workoutExerciseRequest.repeats() != null) workoutExercise.setRepeats(workoutExerciseRequest.repeats());
        if (workoutExerciseRequest.weight() != null) workoutExercise.setWeight(workoutExerciseRequest.weight());
        if (workoutExerciseRequest.restBetweenSets() != null) workoutExercise.setRestBetweenSets(workoutExerciseRequest.restBetweenSets());

        return workoutExercise;
    }

    /**
     * verify witch exactly error occurred after a fail request
     *
     * @author lucasterra
     * @param workoutId the ID of workout
     * @param exerciseId the ID of exercise
     * @throws DataNotFoundException if workout or exercise or both was not found
     */
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

    /**
     * @author lucasterra
     * @param workoutId the ID of workout
     * @param exerciseId the ID of exercise
     * @return WorkoutExerciseReadResponse
     * @throws DataNotFoundException if the exercise or workout or the association between they are not found
     */
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

    /**
     * @author lucasterra
     * @param workoutId the ID of workout
     * @param exerciseId the ID of exercise
     * @throws DataNotFoundException if the exercise or workout or the association between they are not found
     */
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

    /**
     * @author lucasterra
     * @param workoutId the ID of workout
     * @param exerciseId the ID of exercise
     * @param workoutExerciseRequest the DTO of workoutExercise association to save
     * @return WorkoutExerciseReadResponse
     * @throws DataNotFoundException if the exercise or workout are not found
     * @throws DuplicateDataException if a workoutExercise association already exists
     */
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

    /**
     * @author lucasterra
     * @param workoutId the ID of workout
     * @param exerciseId the ID of exercise
     * @param workoutExerciseRequest the DTO of workoutExercise association to update
     * @return WorkoutExerciseReadResponse
     * @throws DataNotFoundException if the exercise or workout or association between they are not found
     */
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


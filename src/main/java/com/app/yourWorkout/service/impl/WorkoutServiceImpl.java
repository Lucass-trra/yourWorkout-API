package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.workout.WorkoutCreateRequest;
import com.app.yourWorkout.DTO.request.workout.WorkoutUpdateRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.entities.User;
import com.app.yourWorkout.entities.Workout;
import com.app.yourWorkout.exception.CollectionEmptyException;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.repository.UserRepository;
import com.app.yourWorkout.repository.WorkoutRepository;
import com.app.yourWorkout.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lucasterra
 */
@Service
@AllArgsConstructor
class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    /**
     * auxiliary method to get the user, reducing coding repeat
     * @author lucasterra
     * @param userId id from user
     * @return User
     * @throws DataNotFoundException if the user was not found to get
     */
    private User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new DataNotFoundException("the user: " + userId + " was not found"));
    }

    /**
     * @author lucasterra
     * @param id the ID of workout
     * @return WorkoutReadResponse
     * @throws DataNotFoundException if the exercise was not found to get
     */
    @Override
    public WorkoutReadResponse findById(int id) {
        return workoutRepository.findById(id)
                .map(WorkoutReadResponse::from)
                .orElseThrow(()-> new DataNotFoundException("the workout: " + id + " was not found"));
    }

    /**
     * @author lucasterra
     * @param userId the ID of user
     * @param name the name of workout
     * @return WorkoutReadResponse
     * @throws DataNotFoundException if the workout was not found for user specified to get
     */
    @Override
    public WorkoutReadResponse findByUserIdAndName(int userId, String name) {
        var user = getUserById(userId);

        return workoutRepository.findByUserAndName(user, name)
                .map(WorkoutReadResponse::from)
                .orElseThrow(()-> new DataNotFoundException("the workout: " + name + " was not found for user: " + userId));
    }

    /**
     * @author lucasterra
     * @param userId the ID of user
     * @param pageable the possible query strings to paginate the list of workouts by user
     * @return Page&lt;WorkoutReadResponse&gt;
     * @throws CollectionEmptyException if the page of workouts by user was empty
     */
    @Override
    public Page<WorkoutReadResponse> findAllByUserId(int userId, Pageable pageable) {
        var user = getUserById(userId);
        var workoutPage = workoutRepository.findAllByUser(user, pageable);

        if(workoutPage.isEmpty()) {
            throw new CollectionEmptyException("there are no workouts for user with id: " + userId);
        }

        return workoutPage.map(WorkoutReadResponse::from);
    }

    /**
     * @author lucasterra
     * @param userId the ID of user
     * @param workoutId the ID of workout
     * @throws DataNotFoundException if the workout was not found for user specified to delete
     */
    @Override
    @Transactional
    public void deleteByUserId(int userId, int workoutId) {
        var user = getUserById(userId);

        workoutRepository.findByUserAndWorkoutId(user, workoutId).ifPresentOrElse(
                workoutRepository::delete,
                () -> {
                    throw new DataNotFoundException("the workout: " + workoutId + " was not found to delete");
                }
        );
    }

    /**
     * @author lucasterra
     * @param userId the ID of user
     * @param workoutRequest the DTO request from workout to save a new workout by user
     * @return WorkoutReadResponse
     * @throws DuplicateDataException if the workout already exists by user
     */
    @Override
    @Transactional
    public WorkoutReadResponse saveByUserId(int userId, WorkoutCreateRequest workoutRequest) {
        var user = getUserById(userId);

        if(workoutRepository.existsByUserAndName(user, workoutRequest.name())) {
            throw new DuplicateDataException("the workout: " + workoutRequest.name() + " already exists for user: " + userId);
        }

        var newWorkout = new Workout(user, workoutRequest.name(), workoutRequest.isCurrent());
        if(workoutRequest.description() != null) newWorkout.setDescription(workoutRequest.description());

        return WorkoutReadResponse.from(
                workoutRepository.save(newWorkout)
        );
    }

    /**
     * @author lucasterra
     * @param userId the ID of user
     * @param workoutId the ID of workout
     * @param workoutRequest the DTO request from workout to update an existing workout by user
     * @return WorkoutReadResponse
     * @throws DataNotFoundException if the workout was not found by user
     * @throws DuplicateDataException if the workout from DTO has the same name of one workout that already exists by user
     */
    @Override
    @Transactional
    public WorkoutReadResponse updateByUserId(int userId, int workoutId, WorkoutUpdateRequest workoutRequest) {
        var user = getUserById(userId);

        var workout = workoutRepository.findByUserAndWorkoutId(user,workoutId)
                .orElseThrow(()-> new DataNotFoundException("the workout: " + workoutId + " was not found for user: " + user.getName()));

        if(workoutRepository.existsByUserAndName(user, workoutRequest.name())) {
          throw new DuplicateDataException("the workout: " + workoutRequest.name() + " already exists for user: " + user.getName() + ", can not update it");
        }

        if(workoutRequest.name() != null) workout.setName(workoutRequest.name());
        if(workoutRequest.isCurrent() != null) workout.setIsCurrent(workoutRequest.isCurrent());
        if(workoutRequest.description() != null) workout.setDescription(workoutRequest.description());

        return WorkoutReadResponse.from(
                workoutRepository.save(workout)
        );
    }
}

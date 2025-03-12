package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.user.UserCreateRequest;
import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.entities.User;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.repository.UserRepository;
import com.app.yourWorkout.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * author lucasterra
 */
@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * @author lucasterra
     * @param userId the id of user
     * @return UserReadResponse
     * @throws DataNotFoundException if the user was not found to get
     */
    @Override
    public UserReadResponse findById(int userId) {
        return userRepository.findById(userId)
                .map(UserReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("user with ID: " + userId + " was not found"));
    }

    /**
     * @author lucasterra
     * @param username the name of user
     * @return UserReadResponse
     * @throws DataNotFoundException if the user was not found to get
     */
    @Override
    public UserReadResponse findByUsername(String username) {
        return userRepository.findByName(username)
                .map(UserReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("user with name" + username + " was not found"));
    }

    /**
     * @author lucasterra
     * @param email the email of user
     * @return UserReadResponse
     * @throws DataNotFoundException if the user was not found to get
     */
    @Override
    public UserReadResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("user with email: " + email + " was not found"));
    }


    /**
     * @author lucasterra
     * @param userId the id of user
     * @throws DataNotFoundException if the user was not found to delete
     */
    @Override
    @Transactional
    public void deleteById(int userId) {
        userRepository.findById(userId).ifPresentOrElse(
                userRepository::delete,
                () -> {
                    throw new DataNotFoundException("user with id: " + userId + " was not found to delete");
                }
        );
    }

    /**
     * @author lucasterra
     * @param username the name of user
     * @throws DataNotFoundException if the user was not found to delete
     */
    @Override
    @Transactional
    public void deleteByUsername(String username) {
        userRepository.findByName(username).ifPresentOrElse(
                userRepository::delete,
                () -> {
                    throw new DataNotFoundException("user with username: " + username + " was not found to delete");
                }
        );
    }

    /**
     * @author lucasterra
     * @param email the email of user
     * @throws DataNotFoundException if the user was not found to delete
     */
    @Override
    @Transactional
    public void deleteByEmail(String email) {
        userRepository.findByEmail(email).ifPresentOrElse(
                userRepository::delete,
                () -> {
                    throw new DataNotFoundException("user with email: " + email + " was not found to delete");
                }
        );
    }

    /**
     * @author lucasterra
     * @param userRequest the DTO request of user to save a new user
     * @return UserReadResponse
     * @throws DuplicateDataException if the user already exists
     */
    @Override
    @Transactional
    public UserReadResponse saveUser(UserCreateRequest userRequest) {
        if (userRepository.existsByName(userRequest.name())) {
            throw new DuplicateDataException("The user with username: " + userRequest.name() + " already exists in the database");
        }

        return UserReadResponse.from(
                userRepository.save(
                        new User(
                                userRequest.name(),
                                userRequest.email(),
                                userRequest.password()
                        )
                )
        );
    }

    /**
     * @author lucasterra
     * @param userId the ID of user
     * @param userRequest the DTO of user to update a existing user
     * @return UserReadResponse
     * @throws DataNotFoundException if the user was not found
     * @throws DuplicateDataException if the user from DTO has the same name of one user that already exists
     */
    @Override
    @Transactional
    public UserReadResponse updateUser(int userId, UserUpdateRequest userRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    if(userRepository.existsByName(userRequest.name())) {
                        throw new DuplicateDataException("the user: " + userRequest.name() + " already exists, can not update him");
                    }
                    Optional.ofNullable(userRequest.name()).ifPresent(user::setName);
                    Optional.ofNullable(userRequest.email()).ifPresent(user::setEmail);
                    Optional.ofNullable(userRequest.password()).ifPresent(user::setPassword);
                    Optional.ofNullable(userRequest.sex()).ifPresent(user::setSex);
                    Optional.ofNullable(userRequest.dateOfBirth()).ifPresent(user::setDateOfBirth);
                    Optional.ofNullable(userRequest.levelOfExperience()).ifPresent(user::setLevelOfExperience);
                    Optional.ofNullable(userRequest.workoutGoal()).ifPresent(user::setWorkoutGoal);
                    Optional.ofNullable(userRequest.height()).ifPresent(user::setHeight);
                    Optional.ofNullable(userRequest.weight()).ifPresent(user::setWeight);

                    return UserReadResponse.from(
                            userRepository.save(user)
                    );
                })
                .orElseThrow(() -> new DataNotFoundException("user with id: " + userId + " was not found to update"));
    }
}

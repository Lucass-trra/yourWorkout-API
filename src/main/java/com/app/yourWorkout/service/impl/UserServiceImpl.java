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

@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserReadResponse findById(int id) {
        return userRepository.findById(id)
                .map(UserReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("user with ID: " + id + " was not found"));
    }

    @Override
    public UserReadResponse findByUsername(String username) {
        return userRepository.findByName(username)
                .map(UserReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("user with name" + username + " was not found"));
    }

    @Override
    public UserReadResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserReadResponse::from)
                .orElseThrow(() -> new DataNotFoundException("user with email: " + email + " was not found"));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userRepository.findById(id).ifPresentOrElse(
                userRepository::delete,
                () -> {
                    throw new DataNotFoundException("user with id: " + id + " was not found to delete");
                }
        );
    }

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

    @Override
    @Transactional
    public UserReadResponse saveUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByName(userCreateRequest.name())) {
            throw new DuplicateDataException("The user with username: " + userCreateRequest.name() + " already exists in the database");
        }

        return UserReadResponse.from(
                userRepository.save(
                        new User(
                                userCreateRequest.name(),
                                userCreateRequest.email(),
                                userCreateRequest.password()
                        )
                )
        );
    }

    @Override
    @Transactional
    public UserReadResponse updateUser(int id, UserUpdateRequest userRequest) {
        return userRepository.findById(id)
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
                .orElseThrow(() -> new DataNotFoundException("user with id: " + id + " was not found to update"));
    }
}

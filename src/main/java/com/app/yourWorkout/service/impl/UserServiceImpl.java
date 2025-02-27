package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.entities.User;
import com.app.yourWorkout.exception.DataAlreadyExistException;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.repository.UserRepository;
import com.app.yourWorkout.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    //READ
    @Override
    public UserReadResponse findById(int id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user with id: " + id + " was not found"));

       return UserReadResponse.from(user);
    }

    @Override
    public UserReadResponse findByUsername(String username) {
        var user = userRepository.findByName(username)
                .orElseThrow(() -> new DataNotFoundException("user with name: " + username + " was not found"));

        return UserReadResponse.from(user);
    }

    @Override
    public UserReadResponse findByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("user with email: " + email + " was not found"));

        return UserReadResponse.from(user);
    }

    //DELETE
    @Override
    public void deleteById(int id) {
        if (!userRepository.existsById(id)) {
            throw new DataNotFoundException("User with id: " + id + " was not found to delete");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByUsername(String username) {
        if (!userRepository.existsByName(username)) {
            throw new DataNotFoundException("User with name: " + username + " was not found to delete");
        }
        userRepository.deleteByName(username);
    }

    @Override
    public void deleteByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new DataNotFoundException("User with email: " + email + " was not found to delete");
        }
        userRepository.deleteByEmail(email);
    }

    //CREATE
    @Override
    public UserReadResponse saveUser(String username, String email, String password) {
        if (userRepository.existsByName(username)) {
            throw new DataAlreadyExistException("The user with username: " + username + " already exists in the database");
        }

        var userSaved = userRepository.save(new User(username,email,password));

        return UserReadResponse.from(userSaved);
    }
    //UPDATE
    @Override
    public UserReadResponse updateUser(int id, UserUpdateRequest userRequest) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user with id: " + id + " was not found to update"));

        if(userRequest.name() != null) user.setName(userRequest.name());
        if(userRequest.email() != null) user.setEmail(userRequest.email());
        if(userRequest.password() != null) user.setPassword(userRequest.password());
        if(userRequest.sex() != null) user.setSex(userRequest.sex());
        if(userRequest.dateOfBirth() != null) user.setDateOfBirth(userRequest.dateOfBirth());
        if(userRequest.levelOfExperience() != null) user.setLevelOfExperience(userRequest.levelOfExperience());
        if(userRequest.workoutGoal() != null) user.setWorkoutGoal(userRequest.workoutGoal());
        if(userRequest.height() != null) user.setHeight(userRequest.height());
        if(userRequest.weight() != null) user.setWeight(userRequest.weight());

        var userSaved = userRepository.save(user);

        return UserReadResponse.from(userSaved);
    }
}

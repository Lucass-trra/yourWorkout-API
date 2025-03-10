package com.app.yourWorkout.service;

import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.entities.User;

public interface UserService {
    //READ
    UserReadResponse findById(int id);
    UserReadResponse findByUsername(String username);
    UserReadResponse findByEmail(String email);

    //DELETE
    void deleteById(int id);
    void deleteByUsername(String username);
    void deleteByEmail(String email);

    //CREATE USER
    UserReadResponse saveUser(String username, String email, String password);

    //UPDATE USER
    UserReadResponse updateUser(int id, UserUpdateRequest userRequest);
}

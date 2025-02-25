package com.app.yourWorkout.service.impl;

import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.service.UserService;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl implements UserService {
    //READ
    @Override
    public UserReadResponse findById(int id) {
        return null;
    }

    @Override
    public UserReadResponse findByUsername(String username) {
        return null;
    }

    @Override
    public UserReadResponse findByEmail(String email) {
        return null;
    }

    //DELETE
    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByUsername(String username) {

    }

    @Override
    public void deleteByEmail(String email) {

    }
    //CREATE
    @Override
    public UserReadResponse saveUser(String username, String email, String password) {
        return null;
    }
    //UPDATE
    @Override
    public UserReadResponse updateUser(int userId, UserUpdateRequest userRequest) {
        return null;
    }
}

package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.request.user.UserCreateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserCreateController {
    private final UserService userService;

    @PostMapping("api/user")
    public ResponseEntity<UserReadResponse> saveUser(@Valid @RequestBody UserCreateRequest userRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(userRequest));
    }
}

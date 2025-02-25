package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.request.user.UserCreateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserCreateController {
    private final UserService userService;

    public UserCreateController(UserService userService) {
        this.userService = userService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<UserReadResponse> saveUser(@RequestBody UserCreateRequest userRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(
                            userRequest.name(),
                            userRequest.email(),
                            userRequest.password()
                        )
                );
    }
}

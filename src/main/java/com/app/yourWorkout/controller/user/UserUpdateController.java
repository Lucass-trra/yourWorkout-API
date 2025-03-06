package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class UserUpdateController {
    private final UserService userService;

    @PutMapping("api/user/{userId}")
    public ResponseEntity<UserReadResponse> updateUser(@PathVariable int userId,
                                                       @Valid @RequestBody UserUpdateRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }
}

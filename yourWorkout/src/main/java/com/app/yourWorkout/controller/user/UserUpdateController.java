package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserUpdateController {
    private final UserService userService;

    public UserUpdateController(UserService userService) {
        this.userService = userService;
    }

    //UPDATE
    @PutMapping("{userId}")
    public ResponseEntity<UserReadResponse> updateUser(@PathVariable int userId,
                                                       @RequestBody UserUpdateRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }
}

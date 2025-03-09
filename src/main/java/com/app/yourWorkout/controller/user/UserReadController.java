package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserReadController {
    private final UserService userService;

    //READ
    @GetMapping("id/{userId}")
    public ResponseEntity<UserReadResponse> getById(@PathVariable int userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserReadResponse> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserReadResponse> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

}

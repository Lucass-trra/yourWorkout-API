package com.app.yourWorkout.controller.user;

import java.lang.Void;
import com.app.yourWorkout.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserDeleteController {
    private final UserService userService;

    public UserDeleteController(UserService userService) {
        this.userService = userService;
    }

    //DELETE
    @DeleteMapping("id/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable int userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("username/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username){
        userService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("email/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email){
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

}

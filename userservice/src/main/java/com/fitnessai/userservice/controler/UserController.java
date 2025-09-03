package com.fitnessai.userservice.controler;

import com.fitnessai.userservice.dto.RegisterRequest;
import com.fitnessai.userservice.dto.UserResponse;
import com.fitnessai.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){

        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> fetchUser(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserProfile(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId){

        return new ResponseEntity<>(userService.validateUser(userId), HttpStatus.OK);
    }
}

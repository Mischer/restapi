package com.mk.restapi.controllers;

import com.mk.restapi.dto.user.RegisterUserDto;
import com.mk.restapi.dto.user.LoginUserDto;
import com.mk.restapi.entity.User;
import com.mk.restapi.exception.AuthenticationException;
import com.mk.restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody RegisterUserDto registerUserDto) {
        User user = userService.createUser(registerUserDto);
        if (user == null) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "User registration is failed"));
        }
        return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginUserDto loginDto) {
        try {
            User user = userService.loginByUsername(loginDto.getUsername(), loginDto.getPassword());
            return ResponseEntity.ok(Collections.singletonMap("message", "Login successful!"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
package com.mk.restapi.controllers;

import com.mk.restapi.dto.user.RegisterUserDto;
import com.mk.restapi.dto.user.LoginUserDto;
import com.mk.restapi.entity.User;
import com.mk.restapi.exception.AuthenticationException;
import com.mk.restapi.security.JwtTokenProvider;
import com.mk.restapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        User user = userService.createUser(registerUserDto);

        if (user == null) {
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "User registration is failed"));
        }
        return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully!"));
    }

    // Endpoint for user authentication
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginUserDto loginUserDto) {
        try {
            // Authenticate user credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.getUsername(),
                            loginUserDto.getPassword()
                    )
            );

            // Generate JWT token
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
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
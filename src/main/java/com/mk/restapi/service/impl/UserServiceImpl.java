package com.mk.restapi.service.impl;

import com.mk.restapi.dto.user.RegisterUserDto;
import com.mk.restapi.entity.User;
import com.mk.restapi.exception.AuthenticationException;
import com.mk.restapi.repository.UserRepository;
import com.mk.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(RegisterUserDto registerUserDto) {

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User loginByUsername(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Invalid credentials!"));
        return user;
    }

    @Override
    public User loginByEmail(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Invalid credentials!"));
        return user;
    }
}

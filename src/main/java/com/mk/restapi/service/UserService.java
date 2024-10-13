package com.mk.restapi.service;

import com.mk.restapi.dto.user.RegisterUserDto;
import com.mk.restapi.entity.User;

import java.util.Optional;

public interface UserService {

    User createUser(RegisterUserDto registerUserDto);


    Optional<User> findById(Long userId);
    User loginByUsername(String username, String rawPassword);
    User loginByEmail(String email, String rawPassword);
}
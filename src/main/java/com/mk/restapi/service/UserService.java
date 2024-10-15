package com.mk.restapi.service;

import com.mk.restapi.dto.user.RegisterUserDto;
import com.mk.restapi.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    User createUser(RegisterUserDto registerUserDto);

    Optional<User> findById(Long userId);

    User loginByUsername(String username, String rawPassword);

    UserDetails loadByUsername(String username);
    User loginByEmail(String email, String rawPassword);
}
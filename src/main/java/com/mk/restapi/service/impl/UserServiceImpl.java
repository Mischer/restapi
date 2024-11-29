package com.mk.restapi.service.impl;

import com.mk.restapi.dto.user.RegisterUserDto;
import com.mk.restapi.entity.User;
import com.mk.restapi.entity.Role;
import com.mk.restapi.entity.RoleName;
import com.mk.restapi.exception.AuthenticationException;
import com.mk.restapi.exception.EntityNotFoundException;
import com.mk.restapi.repository.UserRepository;
import com.mk.restapi.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleServiceImpl roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User createUser(RegisterUserDto registerUserDto) {
        Optional<User> exitUser = userRepository.findByUsername(registerUserDto.getUsername());
        /*if (exitUser != null) { CHECK USER HERE!!!!
            return null;
        }*/

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        Role userRole = roleService.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("User Role not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
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
    public UserDetails loadByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        // Convert User entity to Spring Security's UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public User loginByEmail(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .orElseThrow(() -> new AuthenticationException("Invalid credentials!"));
        return user;
    }
}

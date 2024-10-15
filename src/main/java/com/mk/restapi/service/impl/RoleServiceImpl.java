package com.mk.restapi.service.impl;

import com.mk.restapi.entity.Role;
import com.mk.restapi.entity.RoleName;
import com.mk.restapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}

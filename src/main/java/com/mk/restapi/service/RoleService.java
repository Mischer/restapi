package com.mk.restapi.service;

import com.mk.restapi.entity.Role;
import com.mk.restapi.entity.RoleName;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(RoleName roleName);

    void saveRole(Role role);

}
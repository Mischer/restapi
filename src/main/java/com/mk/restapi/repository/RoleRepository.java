package com.mk.restapi.repository;

import com.mk.restapi.entity.Role;
import com.mk.restapi.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

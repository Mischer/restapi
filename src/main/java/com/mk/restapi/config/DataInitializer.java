package com.mk.restapi.config;

import com.mk.restapi.entity.Role;
import com.mk.restapi.entity.RoleName;
import com.mk.restapi.service.impl.RoleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleServiceImpl roleService;
    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(RoleServiceImpl roleService, JdbcTemplate jdbcTemplate) {
        this.roleService = roleService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--------------INITIALIZE ROLES--------------------");

        // Удаление старого ограничения
        // jdbcTemplate.execute("ALTER TABLE role DROP CONSTRAINT IF EXISTS role_name_check");

        // Пересоздание ограничения с текущими значениями Enum
        // jdbcTemplate.execute("ALTER TABLE role ADD CONSTRAINT role_name_check CHECK (name IN ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_TEACHER'))");

        // Проверка и создание ролей
        if (roleService.findByName(RoleName.ROLE_USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setName(RoleName.ROLE_USER);
            roleService.saveRole(userRole);
        }

        if (roleService.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.ROLE_ADMIN);
            roleService.saveRole(adminRole);
        }

        if (roleService.findByName(RoleName.ROLE_TEACHER).isEmpty()) {
            Role teacherRole = new Role();
            teacherRole.setName(RoleName.ROLE_TEACHER);
            roleService.saveRole(teacherRole);
        }
    }
}
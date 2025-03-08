package com.system.spice.repository;

import com.system.spice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String name);
    @Query("SELECT u.role.roleName FROM User u WHERE u.username = :username")
    String findRoleNameByUsername(@Param("username") String username);
}
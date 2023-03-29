package com.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.user.enitity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

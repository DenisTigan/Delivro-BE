package com.example.Delivro.repository;

import com.example.Delivro.model.ERole;
import com.example.Delivro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}

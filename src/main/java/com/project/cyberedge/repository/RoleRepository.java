package com.project.cyberedge.repository;

import com.project.cyberedge.enums.RoleEnum;
import com.project.cyberedge.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRoleName(RoleEnum name);
}

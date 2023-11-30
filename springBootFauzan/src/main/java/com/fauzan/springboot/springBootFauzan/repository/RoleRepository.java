package com.fauzan.springboot.springBootFauzan.repository;

import com.fauzan.springboot.springBootFauzan.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

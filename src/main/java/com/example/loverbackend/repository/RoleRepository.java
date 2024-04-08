package com.example.loverbackend.repository;

import com.example.loverbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends BaseRepository<Role>, JpaRepository<Role, Long> {
    Role findByName(String name);
}

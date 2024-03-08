package com.example.loverbackend.service;

import com.example.loverbackend.model.Role;
import com.example.loverbackend.model.RoleName;
import com.example.loverbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role findByRoleName(RoleName roleName) {
        Optional<Role> roleOptional = roleRepository.findByRoleName(roleName);
        return roleOptional.orElse(null);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}

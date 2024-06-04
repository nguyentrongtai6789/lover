package com.example.bookstorebackend.service.extend;

import com.example.bookstorebackend.model.Role;
import com.example.bookstorebackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role findByName(String roleName) {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        return roleOptional.orElse(null);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}

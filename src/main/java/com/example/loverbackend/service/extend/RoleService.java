package com.example.loverbackend.service.extend;

import com.example.loverbackend.dto.RoleDTO;
import com.example.loverbackend.mapper.RoleMapper;
import com.example.loverbackend.model.Role;
import com.example.loverbackend.repository.RoleRepository;
import com.example.loverbackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends BaseService<RoleRepository, RoleDTO, Role> {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public RoleDTO getDetails(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.equals(null)) {
            return roleMapper.toDto(roleOptional.get());
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.equals(null)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toDto(roles);
    }

}

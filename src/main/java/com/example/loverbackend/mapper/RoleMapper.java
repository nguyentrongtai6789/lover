package com.example.loverbackend.mapper;

import com.example.loverbackend.dto.RoleDTO;
import com.example.loverbackend.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}

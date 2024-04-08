package com.example.loverbackend.mapper;

import com.example.loverbackend.dto.ProfileUserDTO;
import com.example.loverbackend.model.ProfileUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ProfileUserMapper extends EntityMapper<ProfileUserDTO, ProfileUser> {
}

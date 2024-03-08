package com.example.loverbackend.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <DTO> - DTO type parameter.
 * @param <Entity> - Entity type parameter.
 */

public interface EntityMapper<DTO, Entity> {

    Entity toEntity(DTO dto);

    DTO toDto(Entity entity);

    List<Entity> toEntity(List<DTO> dtoList);

    List<DTO> toDto(List<Entity> entityList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DTO dto, @MappingTarget Entity entity);

}

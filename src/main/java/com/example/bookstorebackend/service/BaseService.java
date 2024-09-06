package com.example.bookstorebackend.service;

import com.example.bookstorebackend.dto.BaseDTO;
import com.example.bookstorebackend.model.BaseEntity;
import com.example.bookstorebackend.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseService <Repository extends BaseRepository<Entity> & JpaRepository<Entity,?>, DTO extends BaseDTO, Entity extends BaseEntity>{
    public abstract void save(Entity entity);
    public abstract DTO getDetails(Long id);
    public abstract boolean deleteById(Long id);
    public abstract List<DTO> findAll();
}
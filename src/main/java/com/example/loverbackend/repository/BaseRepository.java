package com.example.loverbackend.repository;

import com.example.loverbackend.model.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity>{
}

package com.example.bookstorebackend.repository;

import com.example.bookstorebackend.model.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity>{
}

package com.example.loverbackend.repository;

import com.example.loverbackend.model.StatusUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUserRepository extends JpaRepository<StatusUser, Long> {
}

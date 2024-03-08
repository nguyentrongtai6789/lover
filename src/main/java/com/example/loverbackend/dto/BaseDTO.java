package com.example.loverbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long idAccountCreated;
    private Long idAccountUpdated;
    private Long idAccountDeleted;

}

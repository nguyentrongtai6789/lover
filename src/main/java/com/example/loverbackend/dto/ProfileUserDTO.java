package com.example.loverbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileUserDTO extends BaseDTO {
    private Long id;
    private AccountDTO account;
    private String citizenNumber;
    private String lastName;
    private String firstName;
    private String avatarImage;
    private String phoneNumber;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

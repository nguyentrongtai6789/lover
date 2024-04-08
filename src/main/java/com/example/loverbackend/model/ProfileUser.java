package com.example.loverbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProfileUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_account")
    private Account account;
    private String citizenNumber; // số chứng minh nhân dân
    private String lastName; // họ
    private String firstName; // tên
    private String avatarImage; // link ảnh đại diện
    private String phoneNumber;// số điện thoại
    @ManyToOne
    private StatusUser statusUser;
}


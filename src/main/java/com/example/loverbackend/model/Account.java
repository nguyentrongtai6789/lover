package com.example.loverbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String avatar_url;

    @Column(name="status")
    @Enumerated
    private StatusAccount statusAccount;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}

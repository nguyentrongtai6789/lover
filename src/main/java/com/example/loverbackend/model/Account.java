package com.example.loverbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

    @Column(nullable = false)
    private String nickname;

//    @ManyToOne
//    @JoinColumn(name="role_id")
//    private Role roles;
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "accounts_roles",
        joinColumns = {@JoinColumn(name = "account_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
private Set<Role> roles;


}

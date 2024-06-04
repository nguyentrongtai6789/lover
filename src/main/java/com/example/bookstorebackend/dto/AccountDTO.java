package com.example.bookstorebackend.dto;
import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO extends BaseDTO {
    private Long id;
    private String username;
    private String email;
    private String avatar_url;
    private int statusAccount;
    private String nickName;
    private Set<RoleDTO> roles;
}

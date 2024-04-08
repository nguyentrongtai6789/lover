package com.example.loverbackend.dto;
import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO extends BaseDTO {
    private Long id;
    private String username;
    private String email;
    private String nickname;
    private Set<RoleDTO> roles;
}

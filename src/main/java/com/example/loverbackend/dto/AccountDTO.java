package com.example.loverbackend.dto;
import com.example.loverbackend.model.StatusAccount;
import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO extends BaseDTO {
    private Long id;
    private String username;
    private String email;
    private String avatar_url;
    private StatusAccount statusAccount;
    private String nickName;
    private Set<RoleDTO> roles;
}

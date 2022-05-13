package com.letscode.store.dto;

import com.letscode.store.model.RolesEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter @Setter

public class AuthenticationDTO {
    private Boolean authenticated;
    private List<String> roles;

}

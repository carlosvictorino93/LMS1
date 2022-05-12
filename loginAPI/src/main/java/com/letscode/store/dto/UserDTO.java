package com.letscode.store.dto;

import com.letscode.store.model.RolesEnum;
import com.letscode.store.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Boolean enable;
    @NotNull
    private List<RolesEnum> roles;


    public static UserDTO convert(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enable(user.isEnabled())
                .roles(new ArrayList<>(user.getRoles()))
                .build();
    }
}

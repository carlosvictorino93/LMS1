package com.letscode.store.dto;

import com.letscode.store.model.RolesEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

}

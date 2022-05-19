package com.letscode.store.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @NotBlank
    private String name;


}

package com.letscode.store.dto;

import com.letscode.store.model.Client;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationClientDTO {
    private String id;

    private String name;

    private String cpf;

    public static ValidationClientDTO convert(Client client){
        return ValidationClientDTO
                .builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .build();
    }
}

package com.letscode.store.dto;

import com.letscode.store.model.Client;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @NotBlank
    private String name;
    @NotBlank @CPF
    private String cpf;

    public static ClientDTO convert(Client client) {
        return ClientDTO.builder()
                .name(client.getName())
                .cpf(client.getCpf())
                .build();
    }
}

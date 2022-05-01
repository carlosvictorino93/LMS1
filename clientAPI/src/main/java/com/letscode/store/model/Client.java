package com.letscode.store.model;

import com.letscode.store.dto.ClientDTO;
import lombok.*;

import javax.persistence.*;

@Entity(name = "client")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    public static Client convert(ClientDTO dto) {
       return Client.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .build();
    }
}

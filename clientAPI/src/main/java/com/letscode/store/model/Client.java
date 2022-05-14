package com.letscode.store.model;

import com.letscode.store.dto.ClientDTO;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "client")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @MongoId
    private String id;

    private String name;

    @Indexed(unique=true)
    private String cpf;

    public static Client convert(String id, ClientDTO dto) {
       return Client.builder()
               .id(id)
                .name(dto.getName())
                .cpf(dto.getCpf())
                .build();
    }
}

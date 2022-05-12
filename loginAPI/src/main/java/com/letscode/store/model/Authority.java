package com.letscode.store.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authorities")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    private String id;

    private String role;

}

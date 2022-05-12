package com.letscode.store.model;


import com.letscode.store.dto.UserDTO;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;

@Document(collection = "user")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String  id;
    private String username;
    private String password;
    private boolean enabled;
    private Set<RolesEnum> roles;


    public static User convert(UUID id, UserDTO userDTO) {
        return User.builder()
                .id(id.toString())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .enabled(userDTO.getEnable())
                .roles(new HashSet<>(userDTO.getRoles()))
                .build();
    }
}

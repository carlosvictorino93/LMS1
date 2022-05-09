package com.letscode.store.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Document(collection = "authorities")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    @EmbeddedId
    private AuthorityKey authorityKey;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "username")
    private User user;


    @Override
    public String getAuthority() {
        return authorityKey.getAuthority();
    }
}

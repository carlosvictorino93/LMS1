package com.letscode.store.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorityKey implements Serializable {

    @Column(name = "username")
    private String userName;

    @Column(name = "authority")
    private String authority;

}

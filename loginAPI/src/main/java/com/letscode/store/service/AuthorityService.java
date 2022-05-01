package com.letscode.store.service;

import com.letscode.store.model.Authority;
import com.letscode.store.model.AuthorityKey;
import com.letscode.store.model.RolesEnum;
import com.letscode.store.model.User;
import com.letscode.store.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public void saveAuthorities(User userDB, RolesEnum role) {
        AuthorityKey key = AuthorityKey.builder()
                .authority(role.toString())
                .userName(userDB.getUserName())
                .build();

        Authority authority = Authority.builder()
                .authorityKey(key)
                .user(userDB)
                .build();

        authorityRepository.save(authority);
    }
}

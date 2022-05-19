package com.letscode.store.service;

import com.letscode.store.dto.AuthenticationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;


@Service
public class TokenService {

    public AuthenticationDTO getAuthenticate(String token){


        WebClient webClient = WebClient.create("http://localhost:8085");

        AuthenticationDTO authenticationDTO = webClient
                .get()

                .uri("/login")
                .header("authorization", token)
                .retrieve()
                .bodyToMono(AuthenticationDTO.class)
                .block();
        if(authenticationDTO != null) {
            return authenticationDTO;
        }
        return AuthenticationDTO
                .builder()
                .authenticated(false)
                .roles(new ArrayList<>())
                .build();
    }

}

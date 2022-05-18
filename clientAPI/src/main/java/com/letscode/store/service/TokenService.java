package com.letscode.store.service;

import com.letscode.store.dto.AuthenticationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class TokenService {

    public Boolean getAuthenticate(String token){


    WebClient webClient = WebClient.create("http://localhost:8085");

        AuthenticationDTO authenticationDTO = webClient
                .get()
                .uri("/login")
                .header("authorization", token)
                .retrieve()
                .bodyToMono(AuthenticationDTO.class)
                .block();
        if(authenticationDTO != null) {
            return authenticationDTO.getAuthenticated();
        }
        return false;
    }

}

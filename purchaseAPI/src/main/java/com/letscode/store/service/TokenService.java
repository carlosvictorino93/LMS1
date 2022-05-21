package com.letscode.store.service;

import com.letscode.store.dto.TokenDTO;
import com.letscode.store.exception.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Service
public class TokenService {

public TokenDTO getToken(){
    WebClient webClient = WebClient.create("http://localhost:8085");


    String body = "{\n" +
            "\"username\": \"userv\", " +
            "\"password\": \"userv\"\n" +
            "}";

    try{
        return webClient
               .post()
               .uri("/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromValue(body))
               .retrieve()
               .bodyToMono(TokenDTO.class)
               .block();
    }catch (WebClientResponseException e){
        throw new NotFoundException("Usuário não encontrado!!");
    }

}
}

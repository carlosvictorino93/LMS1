package com.letscode.store.service;

import com.letscode.store.dto.ValidationClientDTO;
import com.letscode.store.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ClientService {
    public ValidationClientDTO getClient(String cpf, String token) {
        WebClient webClient = WebClient.create("http://localhost:8081");

        try {
            return webClient
                    .get()
                    .uri("/client/validation/" + cpf)
                    .header("authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(ValidationClientDTO.class)
                    .block();
        }catch (WebClientResponseException e){
            throw new NotFoundException("Cliente " + cpf + " não encontrado!");
        }
    }
}

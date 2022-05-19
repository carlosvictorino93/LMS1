package com.letscode.store.service;

import com.letscode.store.dto.ValidationClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClientService {
    public ValidationClientDTO getClient(String cpf) {
        WebClient webClient = WebClient.create("http://localhost:8081");

        return webClient
                .get()
                .uri("/client/validation/" + cpf)
                .retrieve()
                .bodyToMono(ValidationClientDTO.class)
                .block();
    }
}

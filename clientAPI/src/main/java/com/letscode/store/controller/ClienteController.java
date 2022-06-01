package com.letscode.store.controller;

import com.letscode.store.dto.AuthenticationDTO;
import com.letscode.store.dto.ClientDTO;
import com.letscode.store.dto.ValidationClientDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotAuthorizedException;
import com.letscode.store.model.Client;
import com.letscode.store.service.ClientService;
import com.letscode.store.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClienteController {
    private final ClientService clientService;

    private final TokenService tokenService;



    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Mono<Client> saveClient(@RequestBody @Valid ClientDTO clientDTO, @RequestHeader("authorization") String token) throws AlreadyExistException {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
            return clientService.saveClient(clientDTO);
        }
        throw new NotAuthorizedException("não permitido");
    }

    @GetMapping
    public Flux<Client> getClients(ClientDTO clientDTO, Pageable pageable, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
            return clientService.listClient(clientDTO, pageable);
        }
        throw new NotAuthorizedException("não permitido");
    }



    @PutMapping("/{cpf}")
    public Mono<Client> updateClient(@RequestBody @Valid ClientDTO clientDTO, @PathVariable String cpf, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
            return clientService.updateClient(clientDTO, cpf);
        }
        throw new NotAuthorizedException("não permitido");

    }


    @DeleteMapping("/{cpf}")
    public void deleteClient(@PathVariable String cpf, @RequestHeader("authorization") String token) {
        if (tokenService.getAuthenticate(token).getAuthenticated()){
            clientService.deleteClient(cpf);
        }else{
            throw new NotAuthorizedException("não permitido");
        }
    }
    @GetMapping("/validation/{cpf}")
    public ValidationClientDTO getClientValidation(@PathVariable String cpf, @RequestHeader("authorization") String token){
        AuthenticationDTO authenticationDTO = tokenService.getAuthenticate(token);
        if (
                authenticationDTO.getAuthenticated() && authenticationDTO.getRoles().contains("VALIDATOR")
        ){
            return  ValidationClientDTO.convert(clientService.getClient(cpf));


        }else{
            throw new NotAuthorizedException("não permitido");
        }
    }
}

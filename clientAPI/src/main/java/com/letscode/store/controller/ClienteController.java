package com.letscode.store.controller;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.model.Client;
import com.letscode.store.service.ClientService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClienteController {
    private final ClientService clientService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ClientDTO saveClient(@RequestBody @Valid ClientDTO clientDTO) throws AlreadyExistException {
        return clientService.saveClient(clientDTO);

    }

    @GetMapping
    public Page<ClientDTO> getClient(@QuerydslPredicate(root = Client.class) Predicate predicate, Pageable pageable){
        return clientService.listClient(predicate, pageable);
    }


    @PutMapping("/{cpf}")
    public ClientDTO updateClient(@RequestBody @Valid ClientDTO clientDTO, @PathVariable String cpf) {
        return clientService.updateClient(clientDTO, cpf);
    }


    @DeleteMapping("/{cpf}")
    public void deleteClient(@PathVariable String cpf) {
        clientService.deleteClient(cpf);
    }
}

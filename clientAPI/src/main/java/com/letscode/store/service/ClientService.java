package com.letscode.store.service;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.repository.ClientRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientDTO saveClient(ClientDTO clientDTO) throws AlreadyExistException {
        Optional<Client> client = clientRepository.findClientByCpf(clientDTO.getCpf());
        if(client.isPresent()) throw new AlreadyExistException("Client Already Exist");

        return ClientDTO.convert(clientRepository.save(Client.convert(clientDTO)));

    }

    public Page<ClientDTO> listClient(Predicate predicate, Pageable pageable) {
        return clientRepository.findAll(predicate, pageable).map(ClientDTO::convert);
    }

    public ClientDTO updateClient(ClientDTO clientDTO, String cpf) {
        Client client = getClient(cpf);

        Optional<Client> clientExist = clientRepository.findClientByCpf(clientDTO.getCpf());
        if(clientExist.isPresent()) throw new AlreadyExistException("Client with cpf " + clientDTO.getCpf() + " Already Exist");

        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        return ClientDTO.convert(clientRepository.save(client));
    }

    public void deleteClient(String cpf) {
        Client client = getClient(cpf);
        clientRepository.delete(client);
    }

    public Client getClient(String cpf) {
        Optional<Client> client = clientRepository.findClientByCpf(cpf);
        client.orElseThrow(()-> new NotFoundException("Client not found"));
        return client.get();
    }
}

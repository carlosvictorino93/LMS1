package com.letscode.store.service;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.model.QClient;
import com.letscode.store.model.QPurchase;
import com.letscode.store.repository.ClientRepository;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void SaveClientWithSuccess(){
        Client client = Client.builder()
                .cpf("123")
                .name("Felipe")
                .build();

        Optional<Client> clientEmpty = Optional.empty();

        Mockito.when(clientRepository.findClientByCpf(Mockito.any())).thenReturn(clientEmpty);
        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(client);

        ClientDTO returnedClient = clientService.saveClient(ClientDTO.builder().build());
        Assertions.assertNotNull(returnedClient);
    }

    @Test
    public void clientAlreadyExistOnSave(){
        Client client = Client.builder()
                .cpf("123")
                .name("Felipe")
                .build();

        Mockito.when(clientRepository.findClientByCpf(Mockito.any())).thenReturn(Optional.ofNullable(client));
        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(client);

        Assertions.assertThrows(AlreadyExistException.class, () -> clientService.saveClient(ClientDTO.builder().build()));
    }

    @Test
    public void getListOfClients(){
        List<Client> clients = new ArrayList<>();
        clients.add(Client.builder()
                .cpf("123")
                .name("Felipe")
                .build());
        Page<Client> pageableClients = new PageImpl<>(clients);

        Pageable page = PageRequest.of(0,20);
        Predicate predicate = null;

        Mockito.when(clientRepository.findAll(predicate, page)).thenReturn(pageableClients);

        Page<ClientDTO> returnedClients = clientService.listClient(predicate, page);
        Assertions.assertEquals(1, returnedClients.getSize());
        Assertions.assertTrue(returnedClients.stream().findAny().isPresent());
        Assertions.assertEquals("Felipe", returnedClients.stream().findAny().get().getName());
    }

    @Test
    public void updateAClientThatExist(){
        Client client = Client.builder()
                .cpf("123")
                .name("Felipe")
                .build();

        ClientDTO clientDTO = ClientDTO.builder()
                .cpf("321")
                .name("Antonio")
                .build();

        Mockito.when(clientRepository.findClientByCpf(client.getCpf())).thenReturn(Optional.of(client));
        Mockito.when(clientRepository.findClientByCpf(clientDTO.getCpf())).thenReturn(Optional.empty());
        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(client);

        ClientDTO returnedClientDTO = clientService.updateClient(clientDTO, client.getCpf());
        Assertions.assertEquals("Antonio", returnedClientDTO.getName());


    }

    @Test
    public void updateAClientThatNoExist(){
        Client client = Client.builder()
                .cpf("123")
                .name("Felipe")
                .build();

        ClientDTO clientDTO = ClientDTO.builder()
                .cpf("321")
                .name("Antonio")
                .build();

        Mockito.when(clientRepository.findClientByCpf(client.getCpf())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> clientService.updateClient(clientDTO, client.getCpf()));


    }

    @Test
    public void updateAClientThatExistToaCpfThatExistToo(){
        Client client = Client.builder()
                .cpf("123")
                .name("Felipe")
                .build();

        ClientDTO clientDTO = ClientDTO.builder()
                .cpf("321")
                .name("Antonio")
                .build();

        Mockito.when(clientRepository.findClientByCpf(client.getCpf())).thenReturn(Optional.of(client));
        Mockito.when(clientRepository.findClientByCpf(clientDTO.getCpf())).thenReturn(Optional.of(client));

        Assertions.assertThrows(AlreadyExistException.class, () -> clientService.updateClient(clientDTO, client.getCpf()));

    }

    @Test
    public void testInvokeOfDelete(){
        Client client = Client.builder()
                .cpf("123")
                .name("Felipe")
                .build();

        Mockito.when(clientRepository.findClientByCpf(client.getCpf())).thenReturn(Optional.of(client));
        clientService.deleteClient("123");
        Mockito.verify(clientRepository, Mockito.times(1)).delete(client);
    }
}

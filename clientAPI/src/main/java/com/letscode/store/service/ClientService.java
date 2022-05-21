package com.letscode.store.service;

import com.letscode.store.dto.ClientDTO;
import com.letscode.store.exception.AlreadyExistException;
import com.letscode.store.exception.NotFoundException;
import com.letscode.store.model.Client;
import com.letscode.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final MongoTemplate mongoTemplate;

    public ClientDTO saveClient(ClientDTO clientDTO) throws AlreadyExistException {
        Optional<Client> client = clientRepository.findClientByCpf(clientDTO.getCpf());
        if(client.isPresent()) throw new AlreadyExistException("Client with cpf " + clientDTO.getCpf() + " Already Exist");
        String id = UUID.randomUUID().toString();
        while (clientRepository.findById(id).isPresent()){
            id = UUID.randomUUID().toString();
        }
        return ClientDTO.convert(clientRepository.save(Client.convert(id, clientDTO)));

    }

    public Page<ClientDTO> listClient(ClientDTO clientDTO, Pageable pageable) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (clientDTO.getName() != null && !clientDTO.getName().isEmpty()){
            criteriaList.add(Criteria.where("name").is(clientDTO.getName().toLowerCase()));
        }
        if (clientDTO.getCpf() != null && !clientDTO.getCpf().isEmpty()){
            criteriaList.add(Criteria.where("cpf").is(clientDTO.getCpf()));
        }
        if(criteriaList.size() > 0){
            Criteria criteria = new Criteria().andOperator(criteriaList);
            Query query = Query.query(criteria).with(pageable);
            Query queryCount = Query.query(criteria);

            Long count = mongoTemplate.count(queryCount, Client.class);
            List<ClientDTO> clientDTOListList = mongoTemplate.find(query, Client.class)
                    .stream()
                    .map(ClientDTO::convert)
                    .collect(Collectors.toList());


            return new PageImpl<>(
                    clientDTOListList,
                    pageable,
                    count
            );
        }
        return clientRepository.findAll(pageable).map(ClientDTO::convert);
    }

//    public Page<Client> listClientSpecific(ClientDTO clientDTO, Pageable pageable) {
//        List<Criteria> criteriaList = new ArrayList<>();
//        if (clientDTO.getName() != null && !clientDTO.getName().isEmpty()){
//            criteriaList.add(Criteria.where("name").is(clientDTO.getName()));
//        }
//        if (clientDTO.getCpf() != null && !clientDTO.getCpf().isEmpty()){
//            criteriaList.add(Criteria.where("cpf").is(clientDTO.getCpf()));
//        }
//        if(criteriaList.size() > 0){
//            Criteria criteria = new Criteria().andOperator(criteriaList);
//            Query query = Query.query(criteria).with(pageable);
//            Query queryCount = Query.query(criteria);
//
//            Long count = mongoTemplate.count(queryCount, Client.class);
//            List<Client> clientList = mongoTemplate.find(query, Client.class);
//
//            return new PageImpl<>(
//                    clientList,
//                    pageable,
//                    count
//            );
//        }
//
//        return clientRepository.findAll(pageable);
//
//    }

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

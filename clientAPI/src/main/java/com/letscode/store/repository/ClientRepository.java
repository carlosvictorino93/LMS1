package com.letscode.store.repository;

import com.letscode.store.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
    Optional<Client> findClientByCpf(String cpf);

}

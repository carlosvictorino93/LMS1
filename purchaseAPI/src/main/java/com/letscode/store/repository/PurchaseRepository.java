package com.letscode.store.repository;

import com.letscode.store.model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {

}

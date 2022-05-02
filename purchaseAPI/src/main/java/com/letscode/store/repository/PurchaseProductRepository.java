package com.letscode.store.repository;

import com.letscode.store.model.PurchaseProduct;
import com.letscode.store.model.PurchasedProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, PurchasedProductKey>, QuerydslPredicateExecutor<PurchaseProduct> {
}

package com.letscode.store.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchase is a Querydsl query type for Purchase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchase extends EntityPathBase<Purchase> {

    private static final long serialVersionUID = -1364258321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchase purchase = new QPurchase("purchase");

    public final QClient client;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> purchaseDate = createDateTime("purchaseDate", java.time.LocalDateTime.class);

    public final ListPath<PurchaseProduct, QPurchaseProduct> purchaseProducts = this.<PurchaseProduct, QPurchaseProduct>createList("purchaseProducts", PurchaseProduct.class, QPurchaseProduct.class, PathInits.DIRECT2);

    public final NumberPath<Double> totalPurchased = createNumber("totalPurchased", Double.class);

    public QPurchase(String variable) {
        this(Purchase.class, forVariable(variable), INITS);
    }

    public QPurchase(Path<? extends Purchase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchase(PathMetadata metadata, PathInits inits) {
        this(Purchase.class, metadata, inits);
    }

    public QPurchase(Class<? extends Purchase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new QClient(forProperty("client")) : null;
    }

}


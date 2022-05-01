package com.letscode.store.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPurchasedProductKey is a Querydsl query type for PurchasedProductKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPurchasedProductKey extends BeanPath<PurchasedProductKey> {

    private static final long serialVersionUID = -1586896603L;

    public static final QPurchasedProductKey purchasedProductKey = new QPurchasedProductKey("purchasedProductKey");

    public final NumberPath<Long> idProduct = createNumber("idProduct", Long.class);

    public final NumberPath<Long> idPurchase = createNumber("idPurchase", Long.class);

    public QPurchasedProductKey(String variable) {
        super(PurchasedProductKey.class, forVariable(variable));
    }

    public QPurchasedProductKey(Path<? extends PurchasedProductKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurchasedProductKey(PathMetadata metadata) {
        super(PurchasedProductKey.class, metadata);
    }

}


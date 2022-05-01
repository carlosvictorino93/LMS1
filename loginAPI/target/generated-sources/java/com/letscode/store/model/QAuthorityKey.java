package com.letscode.store.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthorityKey is a Querydsl query type for AuthorityKey
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAuthorityKey extends BeanPath<AuthorityKey> {

    private static final long serialVersionUID = 1624513034L;

    public static final QAuthorityKey authorityKey = new QAuthorityKey("authorityKey");

    public final StringPath authority = createString("authority");

    public final StringPath userName = createString("userName");

    public QAuthorityKey(String variable) {
        super(AuthorityKey.class, forVariable(variable));
    }

    public QAuthorityKey(Path<? extends AuthorityKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorityKey(PathMetadata metadata) {
        super(AuthorityKey.class, metadata);
    }

}


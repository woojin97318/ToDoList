package com.sqistudy.todolist.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity> {

    private static final long serialVersionUID = -1385425097L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final NumberPath<Long> creMemberId = createNumber("creMemberId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> creTime = createDateTime("creTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> updMemberId = createNumber("updMemberId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updTime = createDateTime("updTime", java.time.LocalDateTime.class);

    public QBaseEntity(String variable) {
        super(BaseEntity.class, forVariable(variable));
    }

    public QBaseEntity(Path<? extends BaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntity(PathMetadata metadata) {
        super(BaseEntity.class, metadata);
    }

}


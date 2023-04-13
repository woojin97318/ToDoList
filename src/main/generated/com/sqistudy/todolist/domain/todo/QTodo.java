package com.sqistudy.todolist.domain.todo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = 459814827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodo todo = new QTodo("todo");

    public final com.sqistudy.todolist.domain.QBaseEntity _super = new com.sqistudy.todolist.domain.QBaseEntity(this);

    public final StringPath completeYn = createString("completeYn");

    //inherited
    public final NumberPath<Long> creMemberId = _super.creMemberId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> creTime = _super.creTime;

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Integer> disOrder = createNumber("disOrder", Integer.class);

    public final com.sqistudy.todolist.domain.todogroup.QTodoGroup group;

    public final StringPath title = createString("title");

    public final NumberPath<Long> todoId = createNumber("todoId", Long.class);

    //inherited
    public final NumberPath<Long> updMemberId = _super.updMemberId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updTime = _super.updTime;

    public QTodo(String variable) {
        this(Todo.class, forVariable(variable), INITS);
    }

    public QTodo(Path<? extends Todo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodo(PathMetadata metadata, PathInits inits) {
        this(Todo.class, metadata, inits);
    }

    public QTodo(Class<? extends Todo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new com.sqistudy.todolist.domain.todogroup.QTodoGroup(forProperty("group"), inits.get("group")) : null;
    }

}


package com.sqistudy.todolist.domain.todogroup;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodoGroup is a Querydsl query type for TodoGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoGroup extends EntityPathBase<TodoGroup> {

    private static final long serialVersionUID = 601629281L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodoGroup todoGroup = new QTodoGroup("todoGroup");

    public final com.sqistudy.todolist.domain.QBaseEntity _super = new com.sqistudy.todolist.domain.QBaseEntity(this);

    //inherited
    public final NumberPath<Long> creMemberId = _super.creMemberId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> creTime = _super.creTime;

    public final NumberPath<Long> groupId = createNumber("groupId", Long.class);

    public final com.sqistudy.todolist.domain.member.QMember member;

    public final StringPath name = createString("name");

    public final ListPath<com.sqistudy.todolist.domain.todo.Todo, com.sqistudy.todolist.domain.todo.QTodo> toDoList = this.<com.sqistudy.todolist.domain.todo.Todo, com.sqistudy.todolist.domain.todo.QTodo>createList("toDoList", com.sqistudy.todolist.domain.todo.Todo.class, com.sqistudy.todolist.domain.todo.QTodo.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> updMemberId = _super.updMemberId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updTime = _super.updTime;

    public QTodoGroup(String variable) {
        this(TodoGroup.class, forVariable(variable), INITS);
    }

    public QTodoGroup(Path<? extends TodoGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodoGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodoGroup(PathMetadata metadata, PathInits inits) {
        this(TodoGroup.class, metadata, inits);
    }

    public QTodoGroup(Class<? extends TodoGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.sqistudy.todolist.domain.member.QMember(forProperty("member")) : null;
    }

}


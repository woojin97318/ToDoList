package com.sqistudy.todolist.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1431238485L;

    public static final QMember member = new QMember("member1");

    public final com.sqistudy.todolist.domain.QBaseEntity _super = new com.sqistudy.todolist.domain.QBaseEntity(this);

    public final BooleanPath activated = createBoolean("activated");

    public final SetPath<com.sqistudy.todolist.domain.authority.Authority, com.sqistudy.todolist.domain.authority.QAuthority> authorities = this.<com.sqistudy.todolist.domain.authority.Authority, com.sqistudy.todolist.domain.authority.QAuthority>createSet("authorities", com.sqistudy.todolist.domain.authority.Authority.class, com.sqistudy.todolist.domain.authority.QAuthority.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> creMemberId = _super.creMemberId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> creTime = _super.creTime;

    public final StringPath email = createString("email");

    public final StringPath genderCode = createString("genderCode");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNo = createString("phoneNo");

    public final ListPath<com.sqistudy.todolist.domain.todogroup.TodoGroup, com.sqistudy.todolist.domain.todogroup.QTodoGroup> toDoGroupList = this.<com.sqistudy.todolist.domain.todogroup.TodoGroup, com.sqistudy.todolist.domain.todogroup.QTodoGroup>createList("toDoGroupList", com.sqistudy.todolist.domain.todogroup.TodoGroup.class, com.sqistudy.todolist.domain.todogroup.QTodoGroup.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> updMemberId = _super.updMemberId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updTime = _super.updTime;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


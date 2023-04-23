package com.sqistudy.todolist.domain.tokenblacklist;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenBlacklistCustomRepositoryImpl implements TokenBlacklistCustomRepository {

    private final JPAQueryFactory queryFactory;
}

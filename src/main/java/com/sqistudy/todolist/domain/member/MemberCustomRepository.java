package com.sqistudy.todolist.domain.member;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<Member> findOneWithAuthoritiesByEmail1(String email);
}

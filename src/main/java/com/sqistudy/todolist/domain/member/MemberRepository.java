package com.sqistudy.todolist.domain.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
   @EntityGraph(attributePaths = "authorities")
   Optional<Member> findOneWithAuthoritiesByEmail(String email);
}

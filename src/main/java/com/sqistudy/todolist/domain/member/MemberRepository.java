package com.sqistudy.todolist.domain.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

   @EntityGraph(attributePaths = "authorities")
   Optional<Member> findOneWithAuthoritiesByEmail(String email);
}

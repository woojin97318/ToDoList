package com.sqistudy.todolist.domain.todogroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoGroupRepository extends JpaRepository<TodoGroup, Long> {

    @Query("select g from TodoGroup g where g.member.memberId = :memberId")
    List<TodoGroup> findByMemberId(Long memberId);
}

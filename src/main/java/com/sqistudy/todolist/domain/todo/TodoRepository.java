package com.sqistudy.todolist.domain.todo;

import com.sqistudy.todolist.web.dto.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TodoRepository extends JpaRepository<Todo, Long>, TodoCustomRepository {
    @Query("select new com.sqistudy.todolist.web.dto.TodoDTO(" +
                    "t.todoId, t.date, t.completeYn, t.disOrder, t.title) " +
                    "from Todo t " +
                    "where t.group.groupId = :groupId and t.date = :date")
    List<TodoDTO> findByTodos(Long groupId, LocalDate date);
}

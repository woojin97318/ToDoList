package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.domain.todo.Todo;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoSaveDTO {

    private Long groupId;

    private LocalDate date;

    private String completeYn;

    private Integer disOrder;

    private String title;

    public Todo toTodo(TodoGroup group) {
        return Todo.builder()
                .group(group)
                .date(this.date)
                .completeYn(this.completeYn)
                .disOrder(this.disOrder)
                .title(this.title)
                .build();
    }
}

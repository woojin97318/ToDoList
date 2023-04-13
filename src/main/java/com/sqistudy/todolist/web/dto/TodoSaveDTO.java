package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.domain.todo.Todo;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoSaveDTO {

    private Long groupId;

    private LocalDate date;

    private String title;

    private String completeYn;

    private Integer disOrder;

    public Todo toTodo(TodoGroup group) {
        return Todo.builder()
                .group(group)
                .date(this.date)
                .title(this.title)
                .completeYn(this.completeYn)
                .disOrder(this.disOrder)
                .build();
    }
}

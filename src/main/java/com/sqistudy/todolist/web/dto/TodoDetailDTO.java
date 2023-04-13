package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.domain.todo.Todo;

import java.time.LocalDate;

public class TodoDetailDTO {

    private Long todoId;

    private Long groupId;

    private LocalDate date;

    public static TodoDetailDTO of(Todo todo) {
        TodoDetailDTO detail = new TodoDetailDTO();
        detail.todoId = todo.getTodoId();
        detail.groupId = todo.getGroup().getGroupId();
        detail.date = todo.getDate();

        return detail;
    }
}

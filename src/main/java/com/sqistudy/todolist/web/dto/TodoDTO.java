package com.sqistudy.todolist.web.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoDTO {

    private Long todoId;

    private LocalDate date;

    private String completeYn;

    private Integer disOrder;

    private String title;

    TodoDTO(Long todoId, LocalDate date, String completeYn,
            Integer disOrder, String title) {
        this.todoId = todoId;
        this.date = date;
        this.completeYn = completeYn;
        this.disOrder = disOrder;
        this.title = title;
    }
}

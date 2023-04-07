package com.sqistudy.todolist.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TodoSearchDTO {

    private Long groupId;

    private LocalDate date;
}

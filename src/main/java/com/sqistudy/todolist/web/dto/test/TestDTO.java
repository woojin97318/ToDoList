package com.sqistudy.todolist.web.dto.test;

import com.querydsl.core.annotations.QueryProjection;

public class TestDTO {

    private Long id;

    @QueryProjection
    public TestDTO(Long id) {
        this.id = id;
    }
}

package com.sqistudy.todolist.web.dto.test;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sqistudy.todolist.web.dto.test.QTestDTO is a Querydsl Projection type for TestDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTestDTO extends ConstructorExpression<TestDTO> {

    private static final long serialVersionUID = -896777919L;

    public QTestDTO(com.querydsl.core.types.Expression<Long> id) {
        super(TestDTO.class, new Class<?>[]{long.class}, id);
    }

}


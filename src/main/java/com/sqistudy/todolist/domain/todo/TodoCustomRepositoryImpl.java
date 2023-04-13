package com.sqistudy.todolist.domain.todo;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sqistudy.todolist.web.dto.TodoSearchDTO;
import com.sqistudy.todolist.web.dto.test.QTestDTO;
import com.sqistudy.todolist.web.dto.test.TestDTO;
import com.sqistudy.todolist.web.dto.test.TestSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sqistudy.todolist.domain.todo.QTodo.todo;

@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TestDTO> findTestList(TestSearchDTO search, Pageable pageable) {

        List<TestDTO> contents = queryFactory
                .select(
                        new QTestDTO(
                                todo.todoId.as("id")
                        )
                )
                .from(todo)
                .where(
                        makeSearchExpression(search)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todo.creTime.asc())
                .fetch();

        return PageableExecutionUtils.getPage(contents, pageable, () -> getTodoTotalCount(search));
    }

    private Long getTodoTotalCount(final TestSearchDTO search) {
        return queryFactory
                .select(todo.count())
                .from(todo)
                .where(makeSearchExpression(search))
                .fetchOne();
    }

    private BooleanBuilder makeSearchExpression(final TestSearchDTO search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eqTodoId(search.getTestId()));

        return booleanBuilder;
    }

    private BooleanExpression eqTodoId(String testId) {
        return StringUtils.hasText(testId) ? todo.todoId.eq(Long.valueOf(testId)) : null;
    }
}

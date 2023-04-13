package com.sqistudy.todolist.domain.todo;

import com.sqistudy.todolist.web.dto.test.TestDTO;
import com.sqistudy.todolist.web.dto.test.TestSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoCustomRepository {
    Page<TestDTO> findTestList(TestSearchDTO search, Pageable pageable);
}

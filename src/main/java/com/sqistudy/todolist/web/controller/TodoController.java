package com.sqistudy.todolist.web.controller;

import com.sqistudy.todolist.service.TodoService;
import com.sqistudy.todolist.web.dto.*;
import com.sqistudy.todolist.web.dto.test.TestDTO;
import com.sqistudy.todolist.web.dto.test.TestSearchDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    /**
     * 로그인 사용자의 그룹 리스트 조회
     */
    @GetMapping("/todo-groups/{memberId}")
    public List<TodoGroupDTO> findTodoGroup(@PathVariable Long memberId) {
        return todoService.findTodoGroup(memberId);
    }

    /**
     * 해당 날짜, 해당 그룹의 todos 조회
     */
    @GetMapping("/todos")
    public List<TodoDTO> findByTodos(@ModelAttribute TodoSearchDTO search) {
        return todoService.findByTodos(search);
    }

    /**
     * group 추가
     */
    @PostMapping("/todo/group")
    public void createGroup(@RequestBody TodoGroupSaveDTO group) {
        todoService.createGroup(group);
    }

    /**
     * 그룹 삭제
     */
    @DeleteMapping("/todo/group/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        todoService.deleteGroup(groupId);
    }

    /**
     * to-do 추가
     */
    @PostMapping("/todo")
    public void save(@RequestBody @Valid TodoSaveDTO todo) {
        todoService.save(todo);
    }

    /**
     * to-do 삭제
     */
    @DeleteMapping("/todo/{todoId}")
    public void delete(@PathVariable Long todoId) {
        todoService.delete(todoId);
    }

    /**
     * complete 변경
     */
    @PatchMapping("/complete/{todoId}")
    public void changeCompleteYn(@PathVariable Long todoId) {
        todoService.changeCompleteYn(todoId);
    }

    /**
     * Page 객체, QueryDsl 사용한 select
     * @param search, Pageable(page, size)
     */
    @GetMapping("/test")
    public Page<TestDTO> findTestList(@ModelAttribute TestSearchDTO search,
                                      @PageableDefault Pageable pageable) {
        return todoService.findTestList(search, pageable);
    }
}

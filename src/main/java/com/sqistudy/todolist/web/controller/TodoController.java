package com.sqistudy.todolist.web.controller;

import com.sqistudy.todolist.service.TodoService;
import com.sqistudy.todolist.web.dto.TodoDTO;
import com.sqistudy.todolist.web.dto.TodoGroupDTO;
import com.sqistudy.todolist.web.dto.TodoSaveDTO;
import com.sqistudy.todolist.web.dto.TodoSearchDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * complete 변경
     */
    @PatchMapping("/complete/{todoId}")
    public void changeCompleteYn(@PathVariable Long todoId) {
        todoService.changeCompleteYn(todoId);
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
}

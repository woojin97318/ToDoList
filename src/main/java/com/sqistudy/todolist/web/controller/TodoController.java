package com.sqistudy.todolist.web.controller;

import com.sqistudy.todolist.service.TodoService;
import com.sqistudy.todolist.web.dto.TodoGroupDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo-groups/{memberId}")
    public List<TodoGroupDTO> findTodoGroup(@PathVariable Long memberId) {
        return todoService.findTodoGroup(memberId);
    }
}

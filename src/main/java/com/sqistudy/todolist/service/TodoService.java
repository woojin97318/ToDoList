package com.sqistudy.todolist.service;

import com.sqistudy.todolist.common.exception.ApiException;
import com.sqistudy.todolist.common.exception.ErrorMessage;
import com.sqistudy.todolist.domain.todo.Todo;
import com.sqistudy.todolist.domain.todo.TodoRepository;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import com.sqistudy.todolist.domain.todogroup.TodoGroupRepository;
import com.sqistudy.todolist.web.dto.TodoDTO;
import com.sqistudy.todolist.web.dto.TodoGroupDTO;
import com.sqistudy.todolist.web.dto.TodoSaveDTO;
import com.sqistudy.todolist.web.dto.TodoSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoGroupRepository todoGroupRepository;
    private final TodoRepository todoRepository;

    @Transactional(readOnly = true)
    public List<TodoGroupDTO> findTodoGroup(Long memberId) {
        List<TodoGroupDTO> list = new ArrayList<>();

        List<TodoGroup> todoGroups = todoGroupRepository.findByMemberId(memberId);
        for (TodoGroup todoGroup : todoGroups) {
            list.add(TodoGroupDTO.from(todoGroup));
        }

        return list;
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> findByTodos(TodoSearchDTO search) {
        return todoRepository.findByTodos(search.getGroupId(), search.getDate());
    }

    @Transactional
    public void changeCompleteYn(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ApiException("TodoId Not Found", ErrorMessage.DATA_NOT_FOUND));

        todo.changeCompleteYn();
    }

    @Transactional
    public void save(TodoSaveDTO todo) {
        TodoGroup group = todoGroupRepository.findById(todo.getGroupId())
                .orElseThrow(() -> new ApiException("GroupId Not Found", ErrorMessage.DATA_NOT_FOUND));

        todoRepository.save(todo.toTodo(group));
    }

    @Transactional
    public void delete(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ApiException("TodoId Not Found", ErrorMessage.DATA_NOT_FOUND));

        todoRepository.delete(todo);
    }
}

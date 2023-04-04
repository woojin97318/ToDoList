package com.sqistudy.todolist.service;

import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import com.sqistudy.todolist.domain.todogroup.TodoGroupRepository;
import com.sqistudy.todolist.web.dto.TodoGroupDTO;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private final TodoGroupRepository todoGroupRepository;

    public TodoService(TodoGroupRepository todoGroupRepository) {
        this.todoGroupRepository = todoGroupRepository;
    }

    @Transactional(readOnly = true)
    public List<TodoGroupDTO> findTodoGroup(Long memberId) {
        List<TodoGroupDTO> list = new ArrayList<>();

        List<TodoGroup> todoGroups = todoGroupRepository.findByMemberId(memberId);

        for (TodoGroup todoGroup : todoGroups) {
            TodoGroupDTO dto = new TodoGroupDTO();
            dto.from(todoGroup);
            list.add(dto);
        }

        return list;
    }
}

package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import lombok.Getter;

@Getter
public class TodoGroupDTO {

    private Long groupId;

    private String name;

    public static TodoGroupDTO from(TodoGroup todoGroup) {
        TodoGroupDTO dto = new TodoGroupDTO();
        dto.groupId = todoGroup.getGroupId();
        dto.name = todoGroup.getName();

        return dto;
    }
}

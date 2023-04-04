package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import lombok.Getter;

@Getter
public class TodoGroupDTO {

    private Long groupId;

    private String name;

    public void from(TodoGroup todoGroup) {
        this.groupId = todoGroup.getGroupId();
        this.name = todoGroup.getName();
    }
}

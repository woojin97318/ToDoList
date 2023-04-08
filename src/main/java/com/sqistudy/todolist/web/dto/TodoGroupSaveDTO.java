package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.common.utils.CommonUtil;
import com.sqistudy.todolist.domain.member.Member;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import lombok.Getter;

@Getter
public class TodoGroupSaveDTO {

    private String name;

    public TodoGroup toTodoGroup() {
        Member member = CommonUtil.getMemberInfo();
        return TodoGroup.builder()
                .member(member)
                .name(this.name)
                .build();
    }
}

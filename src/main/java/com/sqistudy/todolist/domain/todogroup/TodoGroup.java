package com.sqistudy.todolist.domain.todogroup;

import com.sqistudy.todolist.domain.BaseEntity;
import com.sqistudy.todolist.domain.todo.Todo;
import com.sqistudy.todolist.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "tb_todo_group", schema = "todolist")
public class TodoGroup {//extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "group")
    private List<Todo> toDoList;
}

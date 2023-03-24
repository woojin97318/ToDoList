package com.sqistudy.todolist.domain.member;

import com.sqistudy.todolist.domain.BaseEntity;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "tb_member", schema = "todolist")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<TodoGroup> toDoGroupList;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender_code", nullable = false, length = 1)
    private String genderCode;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "addr", nullable = false)
    private String addr;
}

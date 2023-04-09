package com.sqistudy.todolist.domain.member;

import com.sqistudy.todolist.domain.BaseEntity;
import com.sqistudy.todolist.domain.authority.Authority;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member", schema = "todolist")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender_code", nullable = false, length = 1)
    private String genderCode;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "tb_member_authority",
            joinColumns = {
                    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_Name", referencedColumnName = "authority_Name")
            })
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "member")
    private List<TodoGroup> toDoGroupList;
}

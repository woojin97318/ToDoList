package com.sqistudy.todolist.domain.todo;

import com.sqistudy.todolist.domain.BaseEntity;
import com.sqistudy.todolist.domain.todogroup.TodoGroup;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_todo", schema = "todolist")
public class Todo {//extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private TodoGroup group;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "complete_yn", nullable = false, length = 1)
    private String completeYn;

    @Column(name = "dis_order", nullable = false)
    private Integer disOrder;

    @Column(name = "title", nullable = false)
    private String title;

    public void changeCompleteYn() {
        this.completeYn = this.completeYn.equals("N") ? "Y" : "N";
    }
}

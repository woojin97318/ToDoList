package com.sqistudy.todolist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @NotNull
    @CreatedDate
    @Column(name = "cre_time", nullable = false)
    protected LocalDateTime creTime;

    @NotBlank
    @CreatedBy
    @Column(name = "cre_memb_id", nullable = false)
    protected String creMembId;

    @NotNull
    @LastModifiedDate
    @Column(name = "upd_time", nullable = false)
    protected LocalDateTime updTime;

    @NotBlank
    @LastModifiedBy
    @Column(name = "upd_memb_id", nullable = false)
    protected String updMembId;

//    abstract public String name();
}

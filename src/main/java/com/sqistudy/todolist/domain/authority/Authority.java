package com.sqistudy.todolist.domain.authority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_authority", schema = "todolist")
public class Authority {

   @Id
   @Column(name = "authority_name")
   private String authorityName;
}

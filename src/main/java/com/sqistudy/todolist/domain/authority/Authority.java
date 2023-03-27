package com.sqistudy.todolist.domain.authority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_authority", schema = "todolist")
public class Authority {

   @Id
   @Column(name = "authority_id")
   private String authorityId;

   @Column(name = "authority_name")
   private String authorityName;
}

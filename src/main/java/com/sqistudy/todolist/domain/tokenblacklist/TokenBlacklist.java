package com.sqistudy.todolist.domain.tokenblacklist;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_token_blacklist", schema = "todolist")
public class TokenBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false)
    private Long tokenId;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @CreatedDate
    @Column(name = "cre_time", nullable = false)
    private LocalDateTime creTime;

    @Column(name = "expire_time", nullable = false)
    private LocalDateTime expireTime;
}

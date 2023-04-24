package com.sqistudy.todolist.service;

import com.sqistudy.todolist.domain.tokenblacklist.TokenBlacklist;
import com.sqistudy.todolist.domain.tokenblacklist.TokenBlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    @Transactional
    public void setLogoutToken(String token, Instant expire) {
        LocalDateTime expireTime =
                LocalDateTime.ofInstant(expire, ZoneId.systemDefault());

        TokenBlacklist tokenBlacklist = TokenBlacklist.builder()
                .token(token)
                .expireTime(expireTime)
                .build();

        tokenBlacklistRepository.save(tokenBlacklist);
    }
}

package com.sqistudy.todolist.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.util.Date;

public class JwtUtils {

    private static final String SECRET_KEY = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK"; // JWT 토큰에 사용할 시크릿 키

    /**
     * 토근 만료시간 조회
     */
    public static Instant getExpiration(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        Date expirationDate = claims.getExpiration();

        return expirationDate.toInstant();
    }
}

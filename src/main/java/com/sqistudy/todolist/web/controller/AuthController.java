package com.sqistudy.todolist.web.controller;

import com.sqistudy.todolist.common.jwt.TokenProvider;
import com.sqistudy.todolist.service.AuthService;
import com.sqistudy.todolist.web.MessageResponse;
import com.sqistudy.todolist.web.dto.LoginDTO;
import com.sqistudy.todolist.web.dto.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AuthService authService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.authService = authService;
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authorize(@Valid @RequestBody LoginDTO login) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + token);

        return new ResponseEntity<>(new TokenDTO(token), httpHeaders, HttpStatus.OK);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = TokenProvider.getTokenFromRequest(request);

        /** TODO
         * redis 서버를 사용하여 Blacklist에 해당 토큰 set
         * 서버 설치 후 실행하여 진행해야함
         */
//        TokenProvider.addToBlacklist(token);

        // RDB를 사용하여 Blacklist 테이블에 해당 토근 set
        authService.setLogoutToken(token);

        return ResponseEntity.ok(new MessageResponse("Logged out successfully!"));
    }
}

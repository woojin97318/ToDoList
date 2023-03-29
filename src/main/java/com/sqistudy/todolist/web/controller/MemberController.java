package com.sqistudy.todolist.web.controller;

import com.sqistudy.todolist.service.MemberService;
import com.sqistudy.todolist.web.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberDTO> signup(@Valid @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.signup(memberDTO));
    }

    /**
     * 모든 사용자 정보 조회
     */
    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDTO> getMyMemberInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities());
    }

    /**
     * Admin 사용자 정보 조회
     */
    @GetMapping("/member/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDTO> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(email));
    }
}

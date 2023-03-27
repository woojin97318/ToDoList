package com.sqistudy.todolist.service;

import com.sqistudy.todolist.domain.authority.Authority;
import com.sqistudy.todolist.domain.member.Member;
import com.sqistudy.todolist.domain.member.MemberRepository;
import com.sqistudy.todolist.common.exception.DuplicateMemberException;
import com.sqistudy.todolist.common.exception.NotFoundMemberException;
import com.sqistudy.todolist.common.utils.SecurityUtil;
import com.sqistudy.todolist.web.dto.MemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberDTO signup(MemberDTO memberDTO) {
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDTO.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityId("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .birthDate(memberDTO.getBirthDate())
                .genderCode(memberDTO.getGenderCode())
                .phoneNo(memberDTO.getPhoneNo())
                .activated(true)
                .authorities(Collections.singleton(authority))
                .build();

        return MemberDTO.from(memberRepository.save(member));
    }

    /**
     * username을 파라미터로 받아서 어떠한 username이든 해당 User객체와 권한 정보 리턴
     */
    @Transactional(readOnly = true)
    public MemberDTO getUserWithAuthorities(String email) {
        return MemberDTO.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    /**
     * 현재 SecurityContext에 저장된 username의 정보만 가져온다.
     */
    @Transactional(readOnly = true)
    public MemberDTO getMyUserWithAuthorities() {
        return MemberDTO.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}

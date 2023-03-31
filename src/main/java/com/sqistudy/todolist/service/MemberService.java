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
            throw new DuplicateMemberException("이미 가입되어 있는 회원입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
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

    @Transactional(readOnly = true)
    public MemberDTO getMyMemberWithAuthorities() {
        return MemberDTO.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

    @Transactional(readOnly = true)
    public MemberDTO getMemberWithAuthorities(String email) {
        return MemberDTO.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }
}

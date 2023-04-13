package com.sqistudy.todolist.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sqistudy.todolist.domain.member.Member;
import com.sqistudy.todolist.service.MemberService;
import com.sqistudy.todolist.web.dto.MemberDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommonUtil {

    private static MemberService memberService = null;

    @Autowired
    private MemberService tempSerivce;

    public static String strMasking(String str) {
        if (StringUtils.hasText(str)) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                builder.append("*");
            }
            return builder.toString();
        }
        return "";
    }

    @PostConstruct
    public void initialize() {
        memberService = tempSerivce;
    }

    public static Long getLoginMemberId() {
        MemberDTO memberDTO = memberService.getMyMemberWithAuthorities1();
        return memberDTO.getMemberId();
    }

    public static Member getMemberInfo() {
        return memberService.findMember(getLoginMemberId());
    }

    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("객체 형식이 잘못됐습니다.");
        }
    }
}

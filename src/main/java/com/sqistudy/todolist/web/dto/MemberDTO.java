package com.sqistudy.todolist.web.dto;

import com.sqistudy.todolist.domain.member.Member;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

   private String email;

   private String password;

   private String nickname;

   private LocalDate birthDate;

   private String genderCode;

   private String phoneNo;

   private Set<AuthorityDTO> authorityDtoSet;

   public static MemberDTO from(Member member) {
      if(member == null) return null;

      return MemberDTO.builder()
              .email(member.getEmail())
              .nickname(member.getNickname())
              .birthDate(member.getBirthDate())
              .genderCode(member.getGenderCode())
              .phoneNo(member.getPhoneNo())
              .authorityDtoSet(member.getAuthorities().stream()
                      .map(authority -> AuthorityDTO.builder().authorityName(authority.getAuthorityName()).build())
                      .collect(Collectors.toSet()))
              .build();
   }
}
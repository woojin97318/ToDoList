package com.sqistudy.todolist.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Member> findOneWithAuthoritiesByEmail1(String email) {
        final String sql = "select * from todolist.tb_member where email = ?";

        List<Member> member =
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class), email);
        return Optional.of(member.get(0));
    }
}

package com.hr.mapper;

import com.hr.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper{
    // 전체 사원 목록
    List<Member> findAll();

    // 이름으로 정보 조회
    Member findByName(String name);

    // 사원 추가
    public void join(Member member);

}

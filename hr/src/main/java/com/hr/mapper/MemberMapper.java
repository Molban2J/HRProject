package com.hr.mapper;

import com.hr.domain.Department;
import com.hr.domain.Member;
import com.hr.domain.Position1;
import com.hr.domain.Position2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper{
    // 전체 사원 목록
    List<Member> findAll();

    // 이름으로 정보 조회
    Member findByName(String name);

    // id로 정보 조회
    Member findById(int m_num);

    // 사원 추가
    public int join(Member member);

    //부서 조회
    public List<Department> getDept();

    //직책 리스트
    public List<Position1> getPosition1();

    //직급 리스트
    public List<Position2> getPosition2();

    //명단 삭제
    public void deleteMember(int m_num);

    public void updateMember(Member member);
}

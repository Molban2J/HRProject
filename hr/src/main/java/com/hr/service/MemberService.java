package com.hr.service;

import com.hr.domain.Department;
import com.hr.domain.Member;
import com.hr.domain.Position1;
import com.hr.domain.Position2;

import java.util.List;

public interface MemberService {
    public List<Member> getMemberList();    //전체 멤버 조회
    public Member getMemberByName(String name); //이름으로 멤버 조회

    public Member getMemberById(int m_num); //id로 멤버 조회
    public int joinMember(Member member);  //멤버 추가

    public List<Department> getDept(); //부서 리스트

    public List<Position1> getPosition1();  //직책 리스트

    public List<Position2> getPosition2();  //직급 리스트

    public void deleteMember(int m_num); // 명단 삭제

    public void updateMember(Member member);
}

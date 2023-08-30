package com.hr.service;

import com.hr.domain.Member;

import java.util.List;

public interface MemberService {
    public List<Member> getMemberList();    //전체 멤버 조회
    public Member getMemberByName(String name); //이름으로 멤버 조회
    public void joinMember(Member member);  //멤버 추가
}

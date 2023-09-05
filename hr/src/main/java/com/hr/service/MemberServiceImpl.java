package com.hr.service;

import com.hr.domain.Department;
import com.hr.domain.Member;
import com.hr.domain.Position1;
import com.hr.domain.Position2;
import com.hr.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberMapper memberMapper;
    @Override
    public List<Member> getMemberList() {
        List<Member> memberList = memberMapper.findAll();

        return memberList;
    }

    @Override
    public Member getMemberByName(String name) {
        return memberMapper.findByName(name);
    }

    @Override
    public Member getMemberById(int m_num) {
        return memberMapper.findById(m_num);
    }

    @Override
    public int joinMember(Member member) {
        return memberMapper.join(member);
    }

    @Override
    public List<Department> getDept() {
        return memberMapper.getDept();
    }

    @Override
    public List<Position1> getPosition1() {
        return memberMapper.getPosition1();
    }

    @Override
    public List<Position2> getPosition2() {
        return memberMapper.getPosition2();
    }

    @Override
    public void deleteMember(int m_num) {
        memberMapper.deleteMember(m_num);
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }
}

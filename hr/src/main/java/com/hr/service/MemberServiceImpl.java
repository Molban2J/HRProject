package com.hr.service;

import com.hr.domain.Member;
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
        return memberMapper.findAll();
    }

    @Override
    public Member getMemberByName(String name) {
        return memberMapper.findByName(name);
    }

    @Override
    public void joinMember(Member member) {
        memberMapper.join(member);
    }
}

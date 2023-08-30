package com.hr.connection;

import com.hr.domain.Member;
import com.hr.domain.MemberStat;
import com.hr.domain.Position;
import com.hr.domain.Position2;
import com.hr.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DBConnectionUtilTest {

    @Autowired
    MemberMapper memberMapper;


    @Test
    @Transactional
    void insert(){
        Member member = new Member(null, "이소일", Position.TEAMMANAGER, Position2.HM, MemberStat.WORKING);
        memberMapper.join(member);
        Member test = memberMapper.findByName("이소일");
        test.setM_num(0);
        assertThat(member).isEqualTo(test);
    }
}

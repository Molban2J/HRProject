package com.hr.connection;

import com.hr.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBConnectionUtilTest {

    @Autowired
    MemberMapper memberMapper;


//    @Test
//    @Transactional
//    void insert(){
//        Member member = new Member(null, "이소일", Position1.TEAMMANAGER, Position2.HM, MemberStat.WORKING, "");
//        memberMapper.join(member);
//        Member test = memberMapper.findByName("이소일");
//        test.setM_num(0);
//        assertThat(member).isEqualTo(test);
//    }
}

package com.hr.database;

import com.hr.mapper.ProjectMapper;
import com.hr.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class QueryTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;

    @Test
    void findMembersEachProjTest(){
        List<Map<String, Object>> list = projectService.findMembersEachProj();
       // List<Object> list = projectMapper.findMembersEachProj();
        System.out.println("list = " + list);
    }

    @Test
    void findLeader(){
        System.out.println("leader = "+ projectService.findProjectLeader());
    }
}

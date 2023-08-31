package com.hr.database;

import com.hr.mapper.ChartMapper;
import com.hr.service.ChartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LineChartTest {

    @Autowired
    ChartService chartService;
    @Autowired
    ChartMapper chartMapper;

    @Test
    void getLineChartDataTest(){
        System.out.println("service = " +chartService.getLineChart());
        System.out.println(chartService.getDate());
        System.out.println(chartService.getImportanceName());
    }
}

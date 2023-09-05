package com.hr.database;

import com.hr.mapper.ChartMapper;
import com.hr.service.ChartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class LineChartTest {

    @Autowired
    ChartService chartService;
    @Autowired
    ChartMapper chartMapper;

    @Test
    void getLineChartDataTest(){
        Date date = new Date();
        System.out.println("service = " +chartService.getLineChart(date));
        System.out.println("service = " +chartService.getLineChart(date).get(1));
        System.out.println("service = " +chartService.getLineChart(date).get(1).get("data"));
        //System.out.println("service = " +chartService.getLineChart().get(1).get("data").get(0));
        System.out.println(chartService.getDate(date));
        System.out.println(chartService.getImportanceName(date));
    }
}

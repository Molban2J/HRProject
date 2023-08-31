package com.hr.service;

import com.hr.domain.LineChart;
import com.hr.mapper.ChartMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements ChartService{

    @Autowired
    ChartMapper chartMapper;

    @Override
    public List<Map<String,List<Integer>>> getLineChart() {
        return chartMapper.getLineChart();
    }

    @Override
    public List<Map<String, Object>> getImportanceName() {
        return null;
    }

    @Override
    public List<String> getDate() {
        List<Date> dateList = chartMapper.getDate();
        List<String> formattedDateList = new ArrayList<>();
        for(Date date : dateList){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            formattedDateList.add(dateFormat.format(date));
        }
        return formattedDateList;
    }
//    @Override
//    public List<LineChart> getLineChart() {
//        return chartMapper.getLineChart();
//    }
}

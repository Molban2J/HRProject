package com.hr.service;

import com.hr.domain.LineChart;
import com.hr.mapper.ChartMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChartServiceImpl implements ChartService{

    @Autowired
    ChartMapper chartMapper;

    @Override
    public List<Map<String,Object>> getLineChart(Date date) {

        //데이터 가공
        List<Map<String,Object>> lineChart = chartMapper.getLineChart(date);
        List<Map<String,Object>> lineChart2 = new ArrayList<>();
        for(int i = 0; i<2; i++){
            //기존 key: data의 value = String형식을 key: data = List<Integer>로 바꿔줌
            //기존 데이터
            String originData = (String)lineChart.get(i).get("data");
            //String[] -> ArrayList
            String[] dataParts = originData.split(",");
            ArrayList<Integer> dataArrayList = new ArrayList<>();
            //새로운 반환값에 들어갈 새로운 Map
            Map<String, Object> dataMap = new HashMap<>();
            for (String dataPart : dataParts) {
                dataArrayList.add(Integer.parseInt(dataPart));
            }
            dataMap.put("data",dataArrayList);
            dataMap.put("label",lineChart.get(i).get("label"));
        lineChart2.add(dataMap);
        }
        return lineChart2;
    }

    @Override
    public List<Map<String, Object>> getImportanceName(Date date) {
        return chartMapper.getImportanceName(date);
    }

    @Override
    public List<String> getDate(Date date) {
        List<Date> dateList = chartMapper.getDate(date);
        List<String> formattedDateList = new ArrayList<>();
        for(Date formatingddate : dateList){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            formattedDateList.add(dateFormat.format(formatingddate));
        }
        return formattedDateList;
    }
//    @Override
//    public List<LineChart> getLineChart() {
//        return chartMapper.getLineChart();
//    }
}

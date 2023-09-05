package com.hr.service;

import com.hr.domain.LineChart;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ChartService {
        public List<Map<String,Object>> getLineChart(Date date);
//    public List<LineChart> getLineChart();

        List<String> getDate(Date date);

        List<Map<String,Object>> getImportanceName(Date date);
}

package com.hr.service;

import com.hr.domain.LineChart;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ChartService {
        public List<Map<String,List<Integer>>> getLineChart();
//    public List<LineChart> getLineChart();

        List<String> getDate();

        List<Map<String,Object>> getImportanceName();
}

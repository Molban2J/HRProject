package com.hr.mapper;

import com.hr.domain.LineChart;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChartMapper {

      List<Map<String,Object>> getLineChart(Date date);
//    List<LineChart> getLineChart();

      List<Date> getDate(Date date);

      List<Map<String, Object>> getImportanceName(Date date);
}

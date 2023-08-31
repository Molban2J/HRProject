package com.hr.mapper;

import com.hr.domain.LineChart;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChartMapper {

      List<Map<String,List<Integer>>> getLineChart();
//    List<LineChart> getLineChart();

      List<Date> getDate();

      List<Map<String, Object>> getImportanceName();
}

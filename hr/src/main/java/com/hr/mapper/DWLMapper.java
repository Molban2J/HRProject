package com.hr.mapper;

import com.hr.domain.DesignWaitingList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DWLMapper {
    List<DesignWaitingList> getAllDWL();    //모든 DWL 조회

    public List<Map<String, Object>> getAllDWLMember(); //모든 DWL 해당 담당자들 조회
}

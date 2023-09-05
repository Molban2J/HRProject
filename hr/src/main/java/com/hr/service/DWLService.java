package com.hr.service;

import com.hr.domain.DesignWaitingList;

import java.util.List;
import java.util.Map;

public interface DWLService {
    public List<DesignWaitingList> getAllDWL(); //모든 DWL 조회
    
    public List<Map<String, Object>> getAllDWLMember(); //모든 DWL 해당 담당자들 조회
}

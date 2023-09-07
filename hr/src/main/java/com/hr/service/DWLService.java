package com.hr.service;

import com.hr.domain.DesignWaitingList;
import com.hr.domain.DesignWaitingListMember;

import java.util.List;
import java.util.Map;

public interface DWLService {
    public List<DesignWaitingList> getAllDWL(); //모든 DWL 조회
    
    public List<Map<String, Object>> getAllDWLMember(); //모든 DWL 해당 담당자들 조회

    public DesignWaitingList findDWLByProjName(String proj_name);

    List<Map<String, Object>> findDWLMBySeq(int dw_seq);

    public void addDWL(DesignWaitingList dwl);

    public void addDWLM(DesignWaitingListMember dwlm);

    public void deleteDWL(int dw_seq);

    public DesignWaitingList findDWLBySeq(int dw_seq);
}

package com.hr.service;

import com.hr.domain.DesignWaitingList;
import com.hr.domain.DesignWaitingListMember;

import java.util.List;
import java.util.Map;

 public interface DWLService {
     List<DesignWaitingList> getAllDWL(); //모든 DWL 조회
    
     List<Map<String, Object>> getAllDWLMember(); //모든 DWL 해당 담당자들 조회

     DesignWaitingList findDWLByProjName(String proj_name);

    List<Map<String, Object>> findDWLMBySeq(int dw_seq);

     void addDWL(DesignWaitingList dwl);

     void addDWLM(DesignWaitingListMember dwlm);

     void deleteDWL(int dw_seq);

     DesignWaitingList findDWLBySeq(int dw_seq);

    void editDWL(DesignWaitingList designWaitingList);

    void deleteDWLMBySeq(int dw_seq);
}

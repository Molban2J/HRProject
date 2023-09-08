package com.hr.mapper;

import com.hr.domain.DesignWaitingList;
import com.hr.domain.DesignWaitingListMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DWLMapper {
    List<DesignWaitingList> getAllDWL();    //모든 DWL 조회

    List<Map<String, Object>> getAllDWLMember(); //모든 DWL 해당 담당자들 조회

    DesignWaitingList findDWLByProjName(String proj_name); //프로젝트 이름으로 dwl찾기

    DesignWaitingList findDWLBySeq(int dw_seq); //프로젝트 seq(id)로 찾기

    List<Map<String, Object>> findDWLMBySeq(int dw_seq);

    void addDWL(DesignWaitingList dwl);   //DesignWaitingList 추가
    
    void addDWLM(DesignWaitingListMember dwlm); //DesignWaitingList Member 추가

    void deleteDWL(int dw_seq); //Design Waiting List 삭제 by dw_seq

    void updateDWL(DesignWaitingList designWaitingList);

    void deleteDWLMBySeq(int dw_seq);
}

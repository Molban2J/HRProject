package com.hr.service;

import com.hr.domain.DesignWaitingList;
import com.hr.domain.DesignWaitingListMember;
import com.hr.mapper.DWLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DWLServiceImpl implements DWLService{

    final
    DWLMapper dwlMapper;
    @Autowired
    public DWLServiceImpl(DWLMapper dwlMapper) {
        this.dwlMapper = dwlMapper;
    }

    @Override
    public List<DesignWaitingList> getAllDWL() {
        /*
        List<DesignWaitingList> list = new ArrayList<>();
        for(DesignWaitingList dwl : dwlMapper.getAllDWL()){
            if(dwl.getEnd_date() ==null){
                dwl.setEnd_date(null);
            }
            list.add(dwl);
        }*/
        return dwlMapper.getAllDWL();
    }

    @Override
    public List<Map<String, Object>> getAllDWLMember() {

        return dwlMapper.getAllDWLMember();
    }

    @Override
    public DesignWaitingList findDWLByProjName(String proj_name) {
        return dwlMapper.findDWLByProjName(proj_name);
    }

    @Override
    public List<Map<String, Object>> findDWLMBySeq(int dw_seq) {
        return dwlMapper.findDWLMBySeq(dw_seq);
    }

    @Override
    public void addDWL(DesignWaitingList dwl) {
        dwlMapper.addDWL(dwl);
    }

    @Override
    public void addDWLM(DesignWaitingListMember dwlm) {
        dwlMapper.addDWLM(dwlm);
    }

    @Override
    public void deleteDWL(int dw_seq) {
        dwlMapper.deleteDWL(dw_seq);
    }

    @Override
    public DesignWaitingList findDWLBySeq(int dw_seq) {
        return dwlMapper.findDWLBySeq(dw_seq);
    }

    @Override
    public void editDWL(DesignWaitingList designWaitingList) {
        dwlMapper.updateDWL(designWaitingList);
    }

    @Override
    public void deleteDWLMBySeq(int dw_seq) {
        dwlMapper.deleteDWLMBySeq(dw_seq);
    }
}

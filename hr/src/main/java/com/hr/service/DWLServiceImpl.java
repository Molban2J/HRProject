package com.hr.service;

import com.hr.domain.DesignWaitingList;
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
}

package com.hr.controller;

import com.hr.domain.LineChart;
import com.hr.service.ChartService;
import com.hr.service.MemberService;
import com.hr.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class MainController {
    @Autowired
    MemberService memberService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ChartService chartService;

    @GetMapping("/")
    public String mainGET(Model model){
        log.info("main GET 실행");
        model.addAttribute("projectMemberList", projectService.findAllProjectMembers());
        model.addAttribute("memberEachProject",projectService.findMembersEachProj());
        model.addAttribute("projectLeader", projectService.findProjectLeader());
        model.addAttribute("projectList", projectService.findAllProjects());
        model.addAttribute("memberList", memberService.getMemberList());
        return "main";
    }

    @GetMapping("/login")
    public String loginGET(){
        log.info("login GET 실행");
        return "login";
    }
    @PostMapping("/login.do")
    public String loginPOST(Model model){
        log.info("login POST 실행");
        model.getAttribute("id");
        model.getAttribute("password");
        return "redirect/main";
    }

    @GetMapping("/tables")
    public String tableTest(){
        return "originSource/tables";
    }

//    @GetMapping("/getLineChart")
//    @ResponseBody
//    public List<Map<String,List<Integer>>> lineChartsGET(Model model){
//        List<Map<String,List<Integer>>> data = chartService.getLineChart();
//        model.addAttribute("chartDate", chartService.getLineChartDate());
//        return data;
//    }
    @GetMapping("/getLineChart")
    @ResponseBody
    public Map<String, Object> lineChartsGET(Model model) {
        Map<String, Object> responseData = new HashMap<>();
        List<Map<String, List<Integer>>> data = chartService.getLineChart();
        List<String> chartDate = chartService.getDate();

        responseData.put("data", data);
        responseData.put("chartDate", chartDate);

        model.addAttribute("responseData", responseData);

        return responseData;
    }

}

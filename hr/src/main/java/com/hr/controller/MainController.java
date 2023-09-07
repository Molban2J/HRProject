package com.hr.controller;

import com.hr.service.ChartService;
import com.hr.service.DWLService;
import com.hr.service.MemberService;
import com.hr.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class MainController {
    final MemberService memberService;

    final ProjectService projectService;

    final ChartService chartService;

    final DWLService dwlService;

    @Autowired
    public MainController(MemberService memberService, ProjectService projectService, ChartService chartService, DWLService dwlService) {
        this.memberService = memberService;
        this.projectService = projectService;
        this.chartService = chartService;
        this.dwlService = dwlService;
    }
    
    //홈 화면
    @GetMapping("/")
    public String mainGET(Model model) {
        log.info("main GET 실행");

        //design waiting list
        model.addAttribute("dwl", dwlService.getAllDWL());
        //design waiting list + 각 프로젝트 참여 멤버 리스트
        model.addAttribute("dwlMemberList", dwlService.getAllDWLMember());
        //각 프로젝트 참여자 리스트
        model.addAttribute("memberEachProject", projectService.findMembersEachProj());
        //프로젝트 책임자 리스트
        model.addAttribute("projectLeader", projectService.findProjectLeader());
        //프로젝트 리스트
        model.addAttribute("projectList", projectService.findAllProjects());
        //사원 전체 목록
        model.addAttribute("memberList", memberService.getMemberList());
        return "main";
    }

    @GetMapping("/login")
    public String loginGET() {
        log.info("login GET 실행");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

//    @PostMapping("/login.do")
//    public String loginPOST(Admin admin){
//        log.info("login POST 실행");
//
//        return "redirect:/";
//    }

    //linchart ajax
    @GetMapping("/getLineChart")
    @ResponseBody
    public Map<String, Object> lineChartsGET(Model model) {

        //log.info("getLineChart GET 실행");
        Map<String, Object> responseData = new HashMap<>();
        //List<Map<String, List<Integer>>> data = chartService.getLineChart();
        Date date;
        if (model.getAttribute("get_date") == null) {
            date = new Date();
        } else {
            date = (Date) model.getAttribute("get_date");
        }

        List<Map<String, Object>> data = chartService.getLineChart(date);
        List<String> chartDate = chartService.getDate(date);

        responseData.put("data", data);
        responseData.put("chartDate", chartDate);
        //log.info("getLineChart GET responseData = " + responseData);
        return responseData;
    }
    
    //DWL 페이지
    @GetMapping("/designWaitingList")
    public String designWaitingListGET(Model model) {
        log.info("designWaitingList GET 접속");
        //design waiting list
        model.addAttribute("dwl", dwlService.getAllDWL());
        log.info("designWaitingList = "+ dwlService.getAllDWL());
        //design waiting list + 각 프로젝트 참여 멤버 리스트
        model.addAttribute("dwlMemberList", dwlService.getAllDWLMember());
        return "/designWaitingList";
    }
    
    // 멤버 명단 페이지
    @GetMapping("/member")
    public String memberGET(Model model){
        log.info("member GET 접속");
        //사원 전체 목록
        model.addAttribute("memberList", memberService.getMemberList());
        return "/member";
    }
}

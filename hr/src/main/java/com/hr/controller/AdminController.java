package com.hr.controller;

import com.hr.CustomDateConverter;
import com.hr.domain.*;
import com.hr.service.DWLService;
import com.hr.service.MemberService;
import com.hr.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    final MemberService memberService;
    final DWLService dwlService;
    final ProjectService projectService;

    @Autowired
    public AdminController(MemberService memberService, DWLService dwlService, ProjectService projectService) {
        this.memberService = memberService;
        this.dwlService = dwlService;
        this.projectService = projectService;
    }

    //date format binder
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateConverter(dateFormat));
    }


    //member admin 페이지 시작 -------------------------------------------------------------
    //사원 명단 추가 page GET
    @GetMapping("/addMember")
    public String addMemberGET(Model model) {
        log.info("addMember GET 실행");
        model.addAttribute("deptList", memberService.getDept());
        model.addAttribute("position1List", memberService.getPosition1());
        model.addAttribute("position2List", memberService.getPosition2());
        return "/admin/member/addMember";
    }

    //사원 명단 추가 POST
    @PostMapping("/addMember.do")
    public String addMemberPOST(Member member) {
        log.info("addMember.do POST 실행");
        log.info("사원 정보 = " + member);
        if (member.getDepartment().isEmpty()) {
            member.setDepartment(null);
        }
        int a = memberService.joinMember(member);
        log.info("명단 추가 성공여부 = " + a);
        return "redirect:/";
    }

    //사원 명단 편집 /삭제 페이지 GET
    @GetMapping("/member")
    public String editMemberListGET(Model model) {
        log.info("editMemberList GET 실행");
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/member/editMemberList";
    }

    //사원 명단에서 삭제 GET
    @GetMapping("/deleteMember")
    public String deleteMemberGET(@RequestParam int m_num) {
        log.info("deleteMember GET 실행");
        log.info("삭제 회원 id = " + m_num);
        memberService.deleteMember(m_num);
        return "redirect:/admin/member";
    }

    //사원 정보 수정 페이지 GET
    @GetMapping("/editMember")
    public String editMemberGET(@RequestParam int m_num, Model model) {
        log.info("editMember GET 실행");
        log.info("수정 회원 id = " + m_num);
        model.addAttribute("member", memberService.getMemberById(m_num));
        model.addAttribute("deptList", memberService.getDept());
        model.addAttribute("position1List", memberService.getPosition1());
        model.addAttribute("position2List", memberService.getPosition2());
        return "/admin/member/editMember";
    }

    // 사원 정보 수정 POST
    @PostMapping("/editMember.do")
    public String editMemberPOST(Member member) {
        log.info("editMember POST 실행");
        log.info("수정 회원 = " + member);
        if (member.getDepartment().isEmpty()) {
            member.setDepartment(null);
        }
        if (member.getJob_field().isEmpty()) {
            member.setJob_field(null);
        }
        memberService.updateMember(member);
        return "redirect:/admin/member";
    }
    //member admin 페이지 끝-------------------------------------------------------------


    //DWL admin 페이지 시작----------------------------------------------------------------

    //DWL 추가 페이지 GET
    @GetMapping("/addDwl")
    public String addDwlGET(Model model) {
        log.info("addDwl GET 실행");
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/dwl/addDwl";
    }

    //DWL 추가 POST
    @PostMapping("/addDwl.do")
    public String addDwlPOST(DesignWaitingList dwl, int memberCount, @RequestParam List<Integer> m_num
    ) {
        log.info("addDwl POST 실행");
        log.info("dwl = " + dwl);
        log.info("memberCount = " + memberCount);
        log.info("member = " + m_num);

        dwlService.addDWL(dwl);
        int dw_seq = dwlService.findDWLByProjName(dwl.getProj_name()).getDw_seq();
        for (int i : m_num) {
            DesignWaitingListMember dwlm = new DesignWaitingListMember(dw_seq, i);
            dwlService.addDWLM(dwlm);
        }
        return "redirect:/designWaitingList";
    }

    //DWL 수정 / 삭제 페이지
    @GetMapping("/designWaitingList")
    public String editDwlGET(Model model) {
        log.info("editDwl GET 실행");
        //design waiting list
        model.addAttribute("dwl", dwlService.getAllDWL());
        //design waiting list + 각 프로젝트 참여 멤버 리스트
        model.addAttribute("dwlMemberList", dwlService.getAllDWLMember());


        return "admin/dwl/editDwl";
    }

    //DWL 삭제 페이지
    @GetMapping("/deleteDw")
    public String deleteDwGET(@RequestParam int dw_seq) {
        log.info("deleteDw GET 실행");
        dwlService.deleteDWL(dw_seq);
        return "redirect:/admin/designWaitingList";
    }

    //DWL 리스트 페이지
    @GetMapping("/editDw")
    public String editDwGET(Model model, @RequestParam int dw_seq) {
        log.info("editDw GET 실행");
        model.addAttribute("dw", dwlService.findDWLBySeq(dw_seq));
        model.addAttribute("dwms", dwlService.findDWLMBySeq(dw_seq));
        model.addAttribute("memberList", memberService.getMemberList());
        return "admin/dwl/editDw";
    }

    //DWL 편집 POST
    @PostMapping("/editDwl.do")
    public String editDwlPOST(DesignWaitingList dwl, @RequestParam List<Integer> m_num
    ) {
        log.info("editDwl POST 실행");
        log.info("dwl = " + dwl);
        log.info("member = " + m_num);

        dwlService.editDWL(dwl);
        dwlService.deleteDWLMBySeq(dwl.getDw_seq());
        for (int i : m_num) {
            DesignWaitingListMember dwlm = new DesignWaitingListMember(dwl.getDw_seq(), i);
            dwlService.addDWLM(dwlm);
        }
        return "redirect:/designWaitingList";
    }
    //DWL admin 페이지 끝 -----------------------------------------------------------------------

    //Project admin 페이지 시작    --------------------------------------------------------------
    @GetMapping("/editProjectList")
    public String editProjectListGET(Model model) {
        log.info("admin editProjectList GET 실행");
        model.addAttribute("projectList", projectService.findAllProjects());
        model.addAttribute("projectMemberList", projectService.findAllProjectMembers());
        return "/admin/project/editProjectList";
    }

    //project add get
    @GetMapping("/addProject")
    public String addProjectGET(Model model) {
        log.info("admin addProject GET 실행");
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/project/addProject";
    }

    //project add post
    @PostMapping("/addProject.do")
    public String addProjectPOST(@RequestParam List<Integer> importance, @RequestParam List<Integer> m_num, Project project) {
        log.info("admin addProject POST 실행");
//        log.info("project = "+project);
//        log.info("importance list = "+importance);
//        log.info("name list = "+m_num);
        setEndDateNull(project);

        int a = projectService.addProject(project);
//        log.info("success = "+a);
        int proj_id = projectService.findProjectByProjName(project.getProj_name()).getProj_id();
        for (int i = 0; i < m_num.size(); i++) {
            ProjectMember projectMember = new ProjectMember(proj_id, m_num.get(i), importance.get(i));
            projectService.addProjectMember(projectMember);
        }
        return "redirect:/";
    }


    @GetMapping("/editProject")
    public String editProjectGET(Model model, @RequestParam int proj_id) {
        log.info("admin editProject GET 실행");
        log.info("proj_name = " + proj_id);

        model.addAttribute("project", projectService.findProjectByProjId(proj_id));
        //log.info("project info = "+ projectService.findProjectByProjName(proj_name));
        model.addAttribute("projMemberList", projectService.findProjMemberByProjId(proj_id));
        //log.info("proj member = "+projectService.findProjMemberByProjId(proj_id));
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/project/editProject";
    }

    @PostMapping("/editProject.do")
    public String editProjectPOST(@RequestParam List<Integer> importance, @RequestParam List<Integer> m_num, Project project) {
        log.info("admin editProject POST 실행");
        log.info("end_date = " + project.getEnd_date());
        setEndDateNull(project);
        projectService.updateProject(project);
        projectService.deleteProjectMember(project.getProj_id());
        for (int i = 0; i < m_num.size(); i++) {
            ProjectMember projectMember = new ProjectMember(project.getProj_id(), m_num.get(i), importance.get(i));
            projectService.addProjectMember(projectMember);
        }
        return "redirect:/admin/editProjectList";
    }

    @GetMapping("/deleteProject")
    public String deleteProjectGET(@RequestParam int proj_id) {
        log.info("admin deleteProject GET 실행");
        log.info("proj_name = " + proj_id + " 삭제");
        projectService.deleteProject(proj_id);
        return "redirect:/admin/editProjectList";
    }

    //end_date 미지정시 set null
    private void setEndDateNull(Project project) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date futureDate = null;

        try {
            futureDate = dateFormat.parse("9998-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (project.getEnd_date().after(futureDate)) {
            project.setEnd_date(null);
        }
    }
    //Project admin 페이지 끝    ----------------------------------------------------------------


    //DailyPlan admin 페이지 시작    ----------------------------------------------------------------
    @GetMapping("/addDailyPlan")
    public String addDailyPlanGET(Model model){
        Timestamp curruntDate = new Timestamp(System.currentTimeMillis());
        model.addAttribute("now", curruntDate);
        model.addAttribute("memberList", memberService.getMemberList());
        return "admin/DailyPlan/addDailyPlan";
    }
    //DailyPlan admin 페이지 끝    ----------------------------------------------------------------
}




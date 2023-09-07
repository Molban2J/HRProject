package com.hr.controller;

import com.hr.CustomDateConverter;
import com.hr.domain.DesignWaitingList;
import com.hr.domain.DesignWaitingListMember;
import com.hr.domain.Member;
import com.hr.service.DWLService;
import com.hr.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    final MemberService memberService;
    final DWLService dwlService;

    @Autowired
    public AdminController(MemberService memberService, DWLService dwlService) {
        this.memberService = memberService;
        this.dwlService = dwlService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateConverter(dateFormat));
    }

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
    public String editDwlGET(Model model){
        log.info("editDwl GET 실행");
        //design waiting list
        model.addAttribute("dwl", dwlService.getAllDWL());
        //design waiting list + 각 프로젝트 참여 멤버 리스트
        model.addAttribute("dwlMemberList", dwlService.getAllDWLMember());


        return "admin/dwl/editDwl";
    }

    //DWL 삭제 페이지
    @GetMapping("/deleteDw")
    public String deleteDwGET(@RequestParam int dw_seq){
        log.info("deleteDw GET 실행");
        dwlService.deleteDWL(dw_seq);
        return "redirect:/admin/designWaitingList";
    }

    @GetMapping("/editDw")
    public String editDwGET(Model model, @RequestParam int dw_seq){
        log.info("editDw GET 실행");
        model.addAttribute("dw", dwlService.findDWLBySeq(dw_seq));
        model.addAttribute("dwms", dwlService.findDWLMBySeq(dw_seq));
        model.addAttribute("memberList", memberService.getMemberList());
        return "admin/dwl/editDw";
    }
}


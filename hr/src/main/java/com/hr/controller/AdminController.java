package com.hr.controller;

import com.hr.domain.Member;
import com.hr.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    final MemberService memberService;

    @Autowired
    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/addMember")
    public String addMemberGET(Model model) {
        log.info("addMember GET 실행");
        model.addAttribute("deptList", memberService.getDept());
        model.addAttribute("position1List", memberService.getPosition1());
        model.addAttribute("position2List", memberService.getPosition2());
        return "/admin/member/addMember";
    }

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

    @GetMapping("/member")
    public String editMemberListGET(Model model) {
        log.info("editMemberList GET 실행");
        model.addAttribute("memberList", memberService.getMemberList());
        return "/admin/member/editMemberList";
    }

    @GetMapping("/deleteMember")
    public String deleteMemberGET(@RequestParam int m_num) {
        log.info("deleteMember GET 실행");
        log.info("삭제 회원 id = " + m_num);
        memberService.deleteMember(m_num);
        return "redirect:/admin/member";
    }

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

    @GetMapping("/addDwl")
    public String addDwlGET(){
        return "/admin/dwl/addDwl";
    }
}

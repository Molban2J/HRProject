package com.hr.controller;

import com.hr.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MainController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String mainGET(Model model){
        log.info("main GET 실행");
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

}

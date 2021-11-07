package org.mystery_muscle.random_gohome_booster.controller;

import org.mystery_muscle.random_gohome_booster.annotation.CurrentUser;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.dto.Login;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("/admin")
public class MemberAdminController {

    private final MemberService memberService;

    @Autowired
    public MemberAdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public String adminRedirectGet(@CurrentUser Member currentUser){
        if(currentUser == null || !currentUser.isAdmin()){
            return "redirect:/login";
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/login")
    public String adminLoginGet(@ModelAttribute Login login){
        return "/admin/login";
    }
    @GetMapping("/home")
    public String adminHomeGet(@ModelAttribute Login login){
        return "/admin/home";
    }

    @PostMapping("/login")
    public String adminLoginPost(@ModelAttribute Login login, BindingResult bindingResult, Model model, HttpServletRequest request){
        Member member = memberService.login(login);
        if(member == null || !member.isAdmin()){
            return "redirect:/admin/login";
        }
        request.getSession().setAttribute("currentUser", member);
        return "redirect:/admim/home";
    }
}

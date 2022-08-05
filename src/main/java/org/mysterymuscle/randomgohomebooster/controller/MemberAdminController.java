package org.mysterymuscle.randomgohomebooster.controller;

import lombok.extern.slf4j.Slf4j;
import org.mysterymuscle.randomgohomebooster.annotation.CurrentUser;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@EnableWebMvc
@Controller
@Slf4j
@RequestMapping("/admin")
public class MemberAdminController {

    private final MemberService memberService;

    // because applying whole of spring security is a big thing
    // we are not going to use it here for now

    // constructor injection
    @Autowired
    public MemberAdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public String adminRedirectGet(@CurrentUser Member currentUser){
        return "redirect:/admin/home";
    }

    @GetMapping("/login")
    public String adminLoginGet(@ModelAttribute Login login, Model model){
        model.addAttribute("login", login);
        return "/admin/login";
    }

    @GetMapping("/home")
    public String adminHomeGet(@CurrentUser Member currentUser, @ModelAttribute Login login, Model model){
        log.info("currentUser name: {}", currentUser.getName());
        model.addAttribute("currentUser", currentUser);
        return "/admin/home";
    }

    @PostMapping("/login")
    public String adminLoginPost(@ModelAttribute Login login, BindingResult bindingResult, Model model, HttpServletRequest request){
        Member member = memberService.login(login);
        if(member == null || !member.isAdmin()){
            return "redirect:/admin/login";
        }
        member = memberService.getMember(member.getLoginId());
        request.getSession().setAttribute("currentUser", member);
        return "redirect:/admin/home";
    }
}

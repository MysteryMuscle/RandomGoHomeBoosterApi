package org.mystery_muscle.random_gohome_booster.controller;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.annotation.CurrentUser;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.dto.Login;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.mystery_muscle.random_gohome_booster.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MemberUserController {

    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @GetMapping("/member")
    public String memberInsertGet(@CurrentUser Member currentUser, @ModelAttribute Member member, Model model) {
        if(currentUser != null){
            return "redirect:/";
        }
        return "/member/form";
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public String memberInsertPost(@ModelAttribute Member member, BindingResult bindingResult, Model model) {
        memberValidator.validate(member, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/member/form";
        }
        member = memberService.insertMember(member);
        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String memberLoginGet(@CurrentUser Member currentUser, @ModelAttribute Login login, Model model){
        if(currentUser != null){
            return "redirect:/";
        }

        return "/member/login";
    }

    @PostMapping("/member/login")
    public String memberLoginPost(@CurrentUser Member currentUser,@ModelAttribute Login login, Model model, HttpServletRequest request){

        if(currentUser != null){
            return "redirect:/";
        }

        Member member = memberService.login(login);

        if(member == null){
            return "/member/login";
        }
        request.getSession().invalidate();
        request.getSession().setAttribute("currentUser", member);
        return "redirect:/";
    }

}

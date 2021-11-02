package org.mystery_muscle.random_gohome_booster.controller;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.mystery_muscle.random_gohome_booster.validator.MemberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class MemberUserController {

    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @GetMapping("/member")
    public String memberInsertGet(@Validated Member member) {
        return "/member/form";
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public String memberInsertPost(Member member, BindingResult bindingResult) {
        memberValidator.validate(member, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/member/form";
        }
        return "redirect:/";
    }

}

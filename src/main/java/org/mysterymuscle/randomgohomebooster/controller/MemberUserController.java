package org.mysterymuscle.randomgohomebooster.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mysterymuscle.randomgohomebooster.annotation.CurrentUser;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.dto.LoginResponse;
import org.mysterymuscle.randomgohomebooster.dto.MemberDto;
import org.mysterymuscle.randomgohomebooster.dto.MemberSignUpRequest;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.mysterymuscle.randomgohomebooster.validator.MemberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberUserController {

    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LoginResponse login(@RequestBody Login login) {
        LoginResponse response = memberService.login(login);
        log.info("login response: {}", response);
        return response;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MemberDto signup(@RequestBody MemberSignUpRequest memberSignUpRequest, BindingResult bindingResult) {
        memberValidator.validate(memberSignUpRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.toString());
        }
        return memberService.insertMember(memberSignUpRequest);
    }


}

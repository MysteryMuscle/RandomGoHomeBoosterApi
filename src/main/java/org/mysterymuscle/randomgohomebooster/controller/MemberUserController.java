package org.mysterymuscle.randomgohomebooster.controller;

import lombok.RequiredArgsConstructor;
import org.mysterymuscle.randomgohomebooster.annotation.CurrentUser;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.mysterymuscle.randomgohomebooster.validator.MemberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

}

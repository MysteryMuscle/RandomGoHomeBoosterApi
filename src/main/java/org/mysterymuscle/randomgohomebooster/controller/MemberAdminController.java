package org.mysterymuscle.randomgohomebooster.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberService memberService;

}

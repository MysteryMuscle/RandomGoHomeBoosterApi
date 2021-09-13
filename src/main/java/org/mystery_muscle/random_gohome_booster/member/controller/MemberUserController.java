package org.mystery_muscle.random_gohome_booster.member.controller;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.member.dto.MemberDto;
import org.mystery_muscle.random_gohome_booster.member.service.MemberService;
import org.mystery_muscle.random_gohome_booster.member.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class MemberUserController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String memberInsertGet(){
        return "/member/form";
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseVo memberInsertPost(MemberDto memberDto){
        return memberService.insertMember(memberDto);
    }

}

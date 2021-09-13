package org.mystery_muscle.random_gohome_booster.member.service;

import org.mystery_muscle.random_gohome_booster.member.dto.MemberDto;
import org.mystery_muscle.random_gohome_booster.member.vo.ResponseVo;

public interface MemberService {
    ResponseVo insertMember(MemberDto memberDto);
}

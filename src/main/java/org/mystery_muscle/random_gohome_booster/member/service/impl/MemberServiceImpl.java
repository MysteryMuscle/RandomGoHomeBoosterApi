package org.mystery_muscle.random_gohome_booster.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.member.dto.MemberDto;
import org.mystery_muscle.random_gohome_booster.member.repository.MemberRepository;
import org.mystery_muscle.random_gohome_booster.member.service.MemberService;
import org.mystery_muscle.random_gohome_booster.member.vo.ResponseVo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseVo insertMember(MemberDto memberDto) {
        return null;
    }
}

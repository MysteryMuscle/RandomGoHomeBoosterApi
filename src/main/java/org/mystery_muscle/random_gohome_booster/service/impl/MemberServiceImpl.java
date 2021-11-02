package org.mystery_muscle.random_gohome_booster.service.impl;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.repository.MemberRepository;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member insertMember(Member memberDto) {
        return null;
    }
}

package org.mystery_muscle.random_gohome_booster.service.impl;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.dto.Login;
import org.mystery_muscle.random_gohome_booster.repository.MemberRepository;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member insertMember(Member member) {
        Member result = memberRepository.save(member);
        return result;
    }

    @Override
    public Member login(Login login) {
        Member fromDB = memberRepository.findAllByLoginId(login.getLoginId());

        return fromDB;
    }
}

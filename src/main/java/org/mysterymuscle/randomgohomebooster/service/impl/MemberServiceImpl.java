package org.mysterymuscle.randomgohomebooster.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.repository.MemberRepository;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member insertMember(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> members = memberRepository.findAllByEmail(member.getEmail());
        if(!members.isEmpty()){
            // 유니크키 따로 처리할 것
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Member login(Login login) {
        Optional<Member> fromDB = memberRepository.findByLoginId(login.getLoginId());
        if(!fromDB.isPresent()){
            log.debug("No loginId Matches");
            return null;
        }

        if(!passwordEncoder.matches(login.getPassword(), fromDB.get().getPassword())){
            log.debug("Password Unmatched");
            return null;
        }
        return fromDB.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(String loginId) {
        Optional<Member> fromDB = memberRepository.findByLoginId(loginId);

        if(!fromDB.isPresent()){
            log.debug("No loginId Matches");
            return null;
        }

        return fromDB.get();
    }
}

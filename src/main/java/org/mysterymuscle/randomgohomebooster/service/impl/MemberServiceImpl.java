package org.mysterymuscle.randomgohomebooster.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mysterymuscle.randomgohomebooster.configuration.JwtTokenProvider;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.dto.LoginResponse;
import org.mysterymuscle.randomgohomebooster.dto.MemberDto;
import org.mysterymuscle.randomgohomebooster.dto.MemberSignUpRequest;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Member insertMember(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> members = memberRepository.findAllByEmail(member.getEmail());
        if (!members.isEmpty()) {
            // 유니크키 따로 처리할 것
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    @Override
    public MemberDto insertMember(MemberSignUpRequest memberSignUpRequest) {
        Member member = Member.createMember(memberSignUpRequest);
        return MemberDto.from(insertMember(member));
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(Login login) {
        Optional<Member> fromDB = memberRepository.findByLoginId(login.getLoginId());
        if (fromDB.isEmpty()) {
            log.info("No loginId Matches");
            return null;
        }

        if (!passwordEncoder.matches(login.getPassword(), fromDB.get().getPassword())) {
            log.info("Password Unmatched");
            return null;
        }
        String token = jwtTokenProvider.createToken(fromDB.get().getLoginId(), fromDB.get().getRoles());

        return new LoginResponse(fromDB.get().getLoginId(), token, jwtTokenProvider.getExpiredAt(token));
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(String loginId) {
        Optional<Member> fromDB = memberRepository.findByLoginId(loginId);

        if (!fromDB.isPresent()) {
            log.debug("No loginId Matches");
            return null;
        }

        return fromDB.get();
    }

}

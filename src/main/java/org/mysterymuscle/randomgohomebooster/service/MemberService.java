package org.mysterymuscle.randomgohomebooster.service;

import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.dto.LoginResponse;
import org.mysterymuscle.randomgohomebooster.dto.MemberDto;
import org.mysterymuscle.randomgohomebooster.dto.MemberSignUpRequest;

public interface MemberService {
    Member insertMember(Member memberDto);

    LoginResponse login(Login login);

    Member getMember(String loginId);

    MemberDto insertMember(MemberSignUpRequest memberSignUpRequest);
}

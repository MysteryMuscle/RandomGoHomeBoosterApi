package org.mysterymuscle.randomgohomebooster.service;

import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;
import org.mysterymuscle.randomgohomebooster.dto.LoginResponse;

public interface MemberService {
    Member insertMember(Member memberDto);

    LoginResponse login(Login login);

    Member getMember(String loginId);
}

package org.mysterymuscle.randomgohomebooster.service;

import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.dto.Login;

public interface MemberService {
    Member insertMember(Member memberDto);

    Member login(Login login);

    Member getMember(String loginId);
}

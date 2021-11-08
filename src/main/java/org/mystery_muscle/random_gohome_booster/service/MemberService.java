package org.mystery_muscle.random_gohome_booster.service;

import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.dto.Login;

public interface MemberService {
    Member insertMember(Member memberDto);

    Member login(Login login);

    Member getMember(String loginId);
}

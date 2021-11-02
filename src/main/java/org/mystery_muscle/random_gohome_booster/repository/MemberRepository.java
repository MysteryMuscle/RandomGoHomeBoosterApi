package org.mystery_muscle.random_gohome_booster.repository;

import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findAllByLoginId(String loginId);
}

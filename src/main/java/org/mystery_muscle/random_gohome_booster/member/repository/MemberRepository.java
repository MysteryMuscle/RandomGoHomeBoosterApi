package org.mystery_muscle.random_gohome_booster.member.repository;

import org.mystery_muscle.random_gohome_booster.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}

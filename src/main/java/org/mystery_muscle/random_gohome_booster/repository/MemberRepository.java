package org.mystery_muscle.random_gohome_booster.repository;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long>{

    List<Member> findAllByEmail(String email);

    Optional<Member> findByLoginId(String loginId);
}

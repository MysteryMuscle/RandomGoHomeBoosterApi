package org.mystery_muscle.random_gohome_booster.repository;

import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
}

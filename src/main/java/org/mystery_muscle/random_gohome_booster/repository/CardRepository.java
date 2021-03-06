package org.mystery_muscle.random_gohome_booster.repository;

import org.mystery_muscle.random_gohome_booster.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {


    List<Card> findAllByCreatorId(Long creatorId);

    @Query("select c from Card c inner join c.deck d where c.deck.id = :deckId")
    List<Card> findAllbyDeckId(@Param("deckId") Long deckId);
}

package org.mysterymuscle.randomgohomebooster.repository;

import org.mysterymuscle.randomgohomebooster.domain.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    
    Deck findByName(String deckName);

    List<Deck> findByOwnerId(Long userId);

}

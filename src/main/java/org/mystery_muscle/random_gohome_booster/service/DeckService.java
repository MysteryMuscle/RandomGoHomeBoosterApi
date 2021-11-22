package org.mystery_muscle.random_gohome_booster.service;

import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeckService {

    public Deck insertDeck(Deck deck);

    public Deck getDeck(Long deckId);

    public Deck updateDeck(Deck deck);

    public void deleteDeck(Long deckId);

    public Deck getDeckByName(String deckName);

    public List<Deck> getDecksByOwner(Long userId);

    public List<Deck> getAllDecks();

    public Page<Deck> getAllDecksPages(int page, int size);

}

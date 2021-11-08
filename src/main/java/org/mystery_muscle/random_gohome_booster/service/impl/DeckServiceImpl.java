package org.mystery_muscle.random_gohome_booster.service.impl;

import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.repository.DeckRepository;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.springframework.stereotype.Service;

@Service
public class DeckServiceImpl implements DeckService {

    private final DeckRepository deckRepository;

    public DeckServiceImpl(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public Deck insertDeck(Deck deck) {
        deck = deckRepository.save(deck);
        return deck;
    }
}

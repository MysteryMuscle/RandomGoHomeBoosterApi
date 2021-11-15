package org.mystery_muscle.random_gohome_booster.service.impl;

import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.repository.DeckRepository;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Deck getDeck(Long deckId) {
        Deck deck = deckRepository.findById(deckId).orElse(null);
        return deck;
    }

    @Override
    public Deck updateDeck(Long deckId, String deckName, String deckDescription) {
        Deck deck = deckRepository.findById(deckId).orElse(null);
        deck.changeDeckInfo(deckName, deckDescription);
        return deck;
    }


    @Override
    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }

    @Override
    public Deck getDeckByName(String deckName) {
        Deck deck = deckRepository.findByName(deckName);
        return deck;
    }

    @Override
    public List<Deck> getDecksByOwner(Long userId) {
        List<Deck> decks = deckRepository.findAllByOwner(userId);
        return decks;
    }

    @Override
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }
}

package org.mysterymuscle.randomgohomebooster.service.impl;

import org.mysterymuscle.randomgohomebooster.domain.Deck;
import org.mysterymuscle.randomgohomebooster.repository.DeckRepository;
import org.mysterymuscle.randomgohomebooster.service.DeckService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    @Transactional(readOnly = true)
    public Deck getDeck(Long deckId) {
        Deck deck = deckRepository.findById(deckId).orElse(null);
        return deck;
    }

    @Override
    public Deck updateDeck(Deck deck) {
        Deck fromDB = deckRepository.findById(deck.getId()).orElse(null);
        fromDB.changeDeckInfo(deck.getName(), deck.getDescription());
        return fromDB;
    }


    @Override
    public void deleteDeck(Long deckId) {
        deckRepository.deleteById(deckId);
    }

    @Override
    @Transactional(readOnly = true)
    public Deck getDeckByName(String deckName) {
        Deck deck = deckRepository.findByName(deckName);
        return deck;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deck> getDecksByOwner(Long userId) {
        List<Deck> decks = deckRepository.findByOwnerId(userId);
        return decks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    @Override
    public Page<Deck> getAllDecksPages(int page, int size) {
        return deckRepository.findAll(PageRequest.of(page, size));
    }
}

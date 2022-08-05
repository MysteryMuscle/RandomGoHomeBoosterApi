package org.mysterymuscle.randomgohomebooster.service.impl;

import lombok.RequiredArgsConstructor;
import org.mysterymuscle.randomgohomebooster.domain.Card;
import org.mysterymuscle.randomgohomebooster.repository.CardRepository;
import org.mysterymuscle.randomgohomebooster.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    @Transactional
    public Card insertCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card updateCard(Card card) {
        Card fromDB = cardRepository.findById(card.getId()).orElse(null);
        if (fromDB != null) {
            fromDB.editCard(card.getName());
            return fromDB;
        }
        return null;
    }

    @Override
    public boolean deleteCard(Long id) {
        Card fromDB = cardRepository.findById(id).orElse(null);
        if (fromDB != null) {
            cardRepository.delete(fromDB);
            return true;
        }
        return false;
    }

    @Override
    public Card getCard(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<Card> getCardsByCreatorId(Long creatorId) {
        return cardRepository.findAllByCreatorId(creatorId);
    }

    @Override
    public List<Card> getCardsByDeckId(Long deckId) {
        return cardRepository.findAllbyDeckId(deckId);
    }
}

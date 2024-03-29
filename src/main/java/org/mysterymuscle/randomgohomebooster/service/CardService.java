package org.mysterymuscle.randomgohomebooster.service;

import org.mysterymuscle.randomgohomebooster.domain.Card;

import java.util.List;

public interface CardService {

    // basic crud

    public Card insertCard(Card card);

    public Card updateCard(Card card);

    public boolean deleteCard(Long id);

    public Card getCard(Long id);

    // custom methods

    // get all cards
    public List<Card> getAllCards();

    // get cards by user
    public List<Card> getCardsByCreatorId(Long userId);

    // get cards by deck
    public List<Card> getCardsByDeckId(Long deckId);


}

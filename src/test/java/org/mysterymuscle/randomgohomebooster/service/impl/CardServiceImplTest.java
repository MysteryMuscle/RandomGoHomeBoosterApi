package org.mysterymuscle.randomgohomebooster.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mysterymuscle.randomgohomebooster.domain.Card;
import org.mysterymuscle.randomgohomebooster.domain.Deck;
import org.mysterymuscle.randomgohomebooster.domain.Member;
import org.mysterymuscle.randomgohomebooster.service.CardService;
import org.mysterymuscle.randomgohomebooster.service.DeckService;
import org.mysterymuscle.randomgohomebooster.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CardServiceImplTest {

    //LOGGER
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CardServiceImplTest.class);

    @Autowired
    private CardService cardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DeckService deckService;

    @BeforeEach
    void setUp() {
        Member member = memberService.getMember("test");
        if (member == null) {
            member = Member.createMember("test", "pwd", "testuser", "test@mail.com");
            member = memberService.insertMember(member);
        }

        List<Deck> allDeck = deckService.getAllDecks();
        if (allDeck.isEmpty()) {
            Deck deck = Deck.createDeck(member, "test", "test description");
            deck = deckService.insertDeck(deck);
        }
    }

    @Test
    void insertCard() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        // when
        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // then
        assertTrue(card.getId() > 0);

    }

    @Test
    void updateCard() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // when
        card.editCard("test2");

        // then
        assertTrue(cardService.getCard(card.getId()).getName().equals("test2"));

    }

    @Test
    void deleteCard() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // when
        cardService.deleteCard(card.getId());

        // then
        assertTrue(cardService.getCard(card.getId()) == null);
    }

    @Test
    void getCard() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // when
        Card card2 = cardService.getCard(card.getId());

        // then
        assertTrue(card2.getName().equals(card.getName()));
    }

    @Test
    void getAllCards() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // when
        List<Card> cards = cardService.getAllCards();

        // then
        assertTrue(cards.size() > 0);
    }

    @Test
    void getCardsByCreatorId() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // when
        List<Card> cards = cardService.getCardsByCreatorId(member.getId());

        // then
        assertTrue(cards.size() > 0);
    }

    @Test
    void getCardsByDeckId() {
        // given
        Member member = memberService.getMember("test");
        List<Deck> decks = deckService.getAllDecks();

        //random deck in the decks
        Deck deck = decks.get((int) (Math.random() * decks.size()));

        Card card = Card.createCard(deck, member, "test");
        card = cardService.insertCard(card);

        // when
        List<Card> cards = cardService.getCardsByDeckId(deck.getId());

        // then
        assertTrue(cards.size() > 0);
    }
}
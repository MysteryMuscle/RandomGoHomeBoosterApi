package org.mystery_muscle.random_gohome_booster.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mystery_muscle.random_gohome_booster.domain.Card;
import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.service.CardService;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
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
    }

    @Test
    void deleteCard() {
    }

    @Test
    void getCard() {
    }

    @Test
    void getAllCards() {
    }

    @Test
    void getCardsByCreatorId() {
    }

    @Test
    void getCardsByDeckId() {
    }
}
package org.mystery_muscle.random_gohome_booster.service.impl;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class DeckServiceImplTest {

    //logger
    private static final Logger LOGGER = LoggerFactory.getLogger(DeckServiceImplTest.class);

    @Autowired
    private DeckService deckService;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void beforeTests() {
        Member member = memberService.getMember("test");
        if(member == null) {
            member = Member.createMember("test", "pwd", "testuser", "test@mail.com");
            member = memberService.insertMember(member);
        }
    }

    @Test
    void insertDeck() {
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        deckService.insertDeck(deck);
        assertNotNull(deckService.getDeckByName("testdeck"));
    }

    @Test
    void getDeck() {
        // given
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        deck = deckService.insertDeck(deck);

        // when
        deckService.getDeck(deck.getId());

        // then
        assertNotNull(deck);

    }

    @Test
    void updateDeck() {
        // given
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        deck = deckService.insertDeck(deck);

        // when
        deck.changeDeckInfo("testdeck2", "description2");
        deckService.updateDeck(deck);

        // then
        assertEquals("testdeck2", deckService.getDeck(deck.getId()).getName());
        assertEquals("description2", deckService.getDeck(deck.getId()).getDescription());
    }

    @Test
    void deleteDeck() {
        // given
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        deck = deckService.insertDeck(deck);

        // when
        deckService.deleteDeck(deck.getId());

        // then
        assertNull(deckService.getDeck(deck.getId()));
    }

    @Test
    void getDeckByName() {
        // given
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        deck = deckService.insertDeck(deck);

        // when
        Deck deck2 = deckService.getDeckByName("testdeck");

        // then
        assertEquals(deck.getId(), deck2.getId());
    }

    @Test
    void getDecksByOwner() {
        // given
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        deck = deckService.insertDeck(deck);

        // when
        deckService.getDecksByOwner(member.getId());

        // then
        assertNotNull(deckService.getDecksByOwner(member.getId()));
    }

    @Test
    void getAllDecks() {
        // given
        Member member = memberService.getMember("test");
        Deck deck = Deck.createDeck(member, "testdeck", "description");
        int randomNumberOfDecks = (int) (Math.random() * 100);
        LOGGER.info("randomNumberOfDecks: " + randomNumberOfDecks);
        for(int i = 0; i < randomNumberOfDecks; i++) {
            deck = deckService.insertDeck(createRandomDeck());
        }

        // when
        deckService.getAllDecks();

        // then
        assertNotNull(deckService.getAllDecks());
    }

    private Deck createRandomDeck(){
        Member member = memberService.getMember("test");
        String randomDeckName = "testdeck" + Math.random();
        String randomDeckDescription = "description" + Math.random();
        return Deck.createDeck(member, randomDeckName, randomDeckDescription);
    }
}
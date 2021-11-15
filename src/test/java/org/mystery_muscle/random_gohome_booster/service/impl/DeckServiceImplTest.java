package org.mystery_muscle.random_gohome_booster.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
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
    }

    @Test
    void updateDeck() {
    }

    @Test
    void deleteDeck() {
    }

    @Test
    void getDeckByName() {
    }

    @Test
    void getDecksByUserId() {
    }

    @Test
    void getAllDecks() {
    }
}
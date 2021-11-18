package org.mystery_muscle.random_gohome_booster.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mystery_muscle.random_gohome_booster.domain.Card;
import org.mystery_muscle.random_gohome_booster.domain.Deck;
import org.mystery_muscle.random_gohome_booster.domain.Item;
import org.mystery_muscle.random_gohome_booster.domain.Member;
import org.mystery_muscle.random_gohome_booster.service.CardService;
import org.mystery_muscle.random_gohome_booster.service.DeckService;
import org.mystery_muscle.random_gohome_booster.service.ItemService;
import org.mystery_muscle.random_gohome_booster.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceImplTest {

    //logger
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplTest.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private CardService cardService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        Member member = memberService.getMember("test");
        if(member == null) {
            member = Member.createMember("test", "pwd", "testuser", "test@gmail.com" );
            member = memberService.insertMember(member);
        }

        List<Deck> decks = deckService.getAllDecks();
        Deck deck = null;
        if(decks.size() == 0) {
            deck = Deck.createDeck(member, "testDeck", "testDeck");
            deck = deckService.insertDeck(deck);
        } else {
            deck = decks.get(0);
        }

        List<Card> cards = cardService.getAllCards();
        Card card = null;
        if(cards.size() == 0) {
            card = Card.createCard(deck, member,"testCard");
            card = cardService.insertCard(card);
        } else {
            card = cards.get(0);
        }
    }


    @Test
    void addItem() {
        // given

        Member member = memberService.getMember("test");
        // get random deck from member
        Deck deck = member.getOwnedDecks().get((int)Math.random() * member.getOwnedDecks().size());
        // get random card from deckCards
        Card card = deck.getCardList().get((int) (Math.random() *  deck.getCardList().size()));

        // when
        Item item = Item.createItem(card, member, "key", "value");
        item = itemService.insertItem(item);


        // then
        assertNotNull(item);

    }

    @Test
    void removeItem() {
        // given
        Member member = memberService.getMember("test");
        // get random deck from member
        Deck deck = member.getOwnedDecks().get((int)Math.random() * member.getOwnedDecks().size());
        // get random card from deckCards
        Card card = deck.getCardList().get((int) (Math.random() *  deck.getCardList().size()));
        Item item = Item.createItem(card, member, "key", "value");
        item = itemService.insertItem(item);
        Long itemId = item.getId();

        // when
        itemService.deleteItem(item);

        // then
        Item fromDB = itemService.getItem(itemId);
        assertNull(fromDB);

    }

    @Test
    void getItem() {
        // given
        Member member = memberService.getMember("test");
        // get random deck from member
        Deck deck = member.getOwnedDecks().get((int)Math.random() * member.getOwnedDecks().size());
        // get random card from deckCards
        Card card = deck.getCardList().get((int) (Math.random() *  deck.getCardList().size()));
        Item item = Item.createItem(card, member, "key", "value");
        item = itemService.insertItem(item);
        Long itemId = item.getId();

        // when
        Item fromDB = itemService.getItem(itemId);

        // then
        assertEquals(item, fromDB);

    }

    @Test
    void updateItem() {
        // given
        Member member = memberService.getMember("test");
        // get random deck from member
        Deck deck = member.getOwnedDecks().get((int)Math.random() * member.getOwnedDecks().size());
        // get random card from deckCards
        Card card = deck.getCardList().get((int) (Math.random() *  deck.getCardList().size()));
        Item item = Item.createItem(card, member, "key", "value");
        item = itemService.insertItem(item);
        Long itemId = item.getId();
        item.setKeyAndValue("key2", "value2");

        // when
        itemService.updateItem(item);

        // then
        Item fromDB = itemService.getItem(itemId);
        assertEquals(item, fromDB);

    }
}
package org.mysterymuscle.randomgohomebooster.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Card {

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;

    public static Card createCard(Deck deck, Member creator, String name) {
        Card card = new Card();
        card.setDeck(deck);
        card.setCreator(creator);
        card.setName(name);
        return card;
    }

    private void setCreator(Member creator) {
        this.creator=creator;
        creator.getOwnedCards().add(this);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDeck(Deck deck) {
        this.deck = deck;
        deck.getCardList().add(this);
    }

    public void editCard(String name) {
        this.name = name;
        this.modDate = LocalDateTime.now();
    }
}


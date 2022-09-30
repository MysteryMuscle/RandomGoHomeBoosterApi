package org.mysterymuscle.randomgohomebooster.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Deck {

    @Id
    @GeneratedValue
    @Column(name = "deck_id")
    private Long id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "deck",
            cascade = CascadeType.ALL)
    private List<MemberDeck> memberDeckList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deck")
    private List<Card> cardList = new ArrayList<>();

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;


    // 생성 메소드
    public static Deck createDeck(Member member, String name, String description) {
        Deck deck = new Deck();
        deck.setOwner(member);
        deck.setName(name);
        deck.setDescription(description);
        return deck;
    }

    // 연관관계 메소드
    private void setOwner(Member member) {
        this.owner=member;
    }

    private void setName(String name) {
        this.name=name;
    }

    private void setDescription(String description) {
        this.description=description;
    }


    // 덱 정보를 수정하는 메소드
    public void changeDeckInfo(String deckName, String deckDescription) {
        if(StringUtils.hasText(deckName)) {
            this.name = deckName;
        }
        if(StringUtils.hasText(deckDescription)) {
            this.description = deckDescription;
        }
        this.modDate = LocalDateTime.now();
    }
}

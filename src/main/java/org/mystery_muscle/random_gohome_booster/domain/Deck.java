package org.mystery_muscle.random_gohome_booster.domain;

import lombok.Getter;
import lombok.Setter;
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
public class Deck {

    @Id
    @GeneratedValue
    @Column(name = "deck_id")
    private Long id;
    private String name;

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
    public static Deck createDeck(Member member, String name){
        Deck deck = new Deck();
        deck.setOwner(member);
        deck.setName(name);
        return deck;
    }

    // 연관관계 메소드
    private void setOwner(Member member) {
        this.owner=member;
        member.getOwnedDecks().add(this);
    }

    private void setName(String name) {
        this.name=name;
    }
}

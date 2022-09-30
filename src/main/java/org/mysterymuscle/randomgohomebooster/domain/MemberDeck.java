package org.mysterymuscle.randomgohomebooster.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class MemberDeck {

    @Id
    @GeneratedValue
    @Column(name = "member_deck_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @CreatedDate
    private LocalDateTime regDate;

    public static MemberDeck createMemberDeck(Member member, Deck deck){
        MemberDeck memberDeck = new MemberDeck();
        memberDeck.setMember(member);
        memberDeck.setDeck(deck);
        return memberDeck;
    }

    private void setDeck(Deck deck) {
        this.deck=deck;
        deck.getMemberDeckList().add(this);
    }

    private void setMember(Member member) {
        this.member=member;
    }
}

package org.mystery_muscle.random_gohome_booster.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ApprovedDeck {

    @Id
    @GeneratedValue
    @Column(name = "approved_deck_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    private LocalDateTime regDate;

}

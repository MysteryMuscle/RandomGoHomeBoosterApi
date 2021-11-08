package org.mystery_muscle.random_gohome_booster.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Deck {

    @Id @GeneratedValue
    @Column(name ="deck_id")
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY
            , mappedBy = "deck")
    private List<MemberDeck> memberDeckList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member owner;

    @OneToMany(mappedBy = "deck_id")
    private List<Card> card = new ArrayList<>();

    private LocalDateTime regDate;
}

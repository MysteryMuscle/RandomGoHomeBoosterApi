package org.mystery_muscle.random_gohome_booster.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Deck {

    @Id @GeneratedValue
    @Column(name ="deck_id")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    private LocalDateTime regDate;
}

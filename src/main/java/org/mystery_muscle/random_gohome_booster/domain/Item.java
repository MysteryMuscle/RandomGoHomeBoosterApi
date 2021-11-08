package org.mystery_muscle.random_gohome_booster.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    private String key;
    private String value;

}

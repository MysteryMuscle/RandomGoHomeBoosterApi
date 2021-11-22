package org.mystery_muscle.random_gohome_booster.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    private String keyName;
    private String keyValue;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member creator;

    @CreatedDate
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    public static Item createItem(Card card, Member creator, String key, String value){
        Item item = new Item();
        item.setCard(card);
        item.setKeyAndValue(key,value);
        item.setCreator(creator);
        return item;
    }

    private void setCreator(Member creator) {
        this.creator=creator;
    }

    public void setKeyAndValue(String key, String value) {
        this.keyName =key;
        this.keyValue =value;
    }

    private void setCard(Card card) {
        this.card=card;
        card.getItemList().add(this);
    }
}

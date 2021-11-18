package org.mystery_muscle.random_gohome_booster.service;

import org.mystery_muscle.random_gohome_booster.domain.Item;

public interface ItemService {

    public Item insertItem(Item item);

    public void deleteItem(Item item);

    public Item getItem(Long id);

    public void updateItem(Item item);




}

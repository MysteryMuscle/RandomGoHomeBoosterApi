package org.mysterymuscle.randomgohomebooster.service;

import org.mysterymuscle.randomgohomebooster.domain.Item;

public interface ItemService {

    public Item insertItem(Item item);

    public void deleteItem(Item item);

    public Item getItem(Long id);

    public void updateItem(Item item);




}

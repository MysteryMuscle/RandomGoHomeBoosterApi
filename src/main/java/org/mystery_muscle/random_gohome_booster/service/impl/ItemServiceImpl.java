package org.mystery_muscle.random_gohome_booster.service.impl;

import lombok.RequiredArgsConstructor;
import org.mystery_muscle.random_gohome_booster.domain.Item;
import org.mystery_muscle.random_gohome_booster.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;


    @Override
    @Transactional
    public Item insertItem(Item item) {
        // insert item into the db
        return itemRepository.save(item);
    }


    @Override
    @Transactional
    public void deleteItem(Item item) {
        // delete item from the db
        itemRepository.delete(item);
    }


    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateItem(Item item) {
        // get item from db
        Item fromDB = itemRepository.findById(item.getId()).orElse(null);
        // update item
        if(fromDB != null) {
            fromDB.setKeyAndValue(item.getKey(), item.getValue());
        }
    }
}

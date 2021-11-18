package org.mystery_muscle.random_gohome_booster.service.impl;

import org.mystery_muscle.random_gohome_booster.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}

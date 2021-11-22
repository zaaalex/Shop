package com.zorkoalex.shop.goods;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository <entity, type first key>
public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
    boolean existsByName(String name);
    CakeEntity findCakeEntityById(Long id);
}

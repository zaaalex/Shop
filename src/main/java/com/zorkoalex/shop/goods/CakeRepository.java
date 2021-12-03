package com.zorkoalex.shop.goods;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository <entity, type first key>
public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
    //JPA - api (спецификация) для того, чтобы хранить объекты java в базе данных
    //@Entity @column @id аннотации помечают код java для соотношения с базой данных

    boolean existsByName(String name);
    CakeEntity findCakeEntityById(Long id);
}

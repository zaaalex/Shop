package com.zorkoalex.shop.database.cakes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Repository <entity, type first key>
public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
    //JPA - api (спецификация) для того, чтобы хранить объекты java в базе данных
    //@Entity @column @id аннотации помечают код java для соотношения с базой данных

    @Query("SELECT Cake FROM CakeEntity Cake WHERE Cake.id=:id")
    CakeEntity findCakeee(@Param("id") Long id);
}

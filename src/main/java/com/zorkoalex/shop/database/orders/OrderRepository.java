package com.zorkoalex.shop.database.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    @Query("SELECT Orders FROM OrderEntity Orders JOIN Orders.user User WHERE User.number=:NUMBER")
    List<OrderEntity> findOrderEntitiesByUser_Numberrr(@Param("NUMBER") String number);

    //List<OrderEntity> findOrderEntitiesByUser_Number(String number); JPA
    boolean existsByUser_Number(String number);
}

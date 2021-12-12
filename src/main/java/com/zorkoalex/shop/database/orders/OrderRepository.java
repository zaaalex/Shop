package com.zorkoalex.shop.database.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
    List<OrderEntity> findOrderEntitiesByUser_Number(String number);
}

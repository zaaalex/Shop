package com.zorkoalex.shop.orders.purchase;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    boolean existsByOrder(Order order);
    boolean existsByCake(Cake cake);
}

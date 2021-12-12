package com.zorkoalex.shop.database.purchase;

import com.zorkoalex.shop.dto.cake.Cake;
import com.zorkoalex.shop.dto.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    boolean existsByOrder(Order order);
    boolean existsByCake(Cake cake);
}

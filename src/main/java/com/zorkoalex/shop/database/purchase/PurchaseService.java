package com.zorkoalex.shop.database.purchase;

import com.zorkoalex.shop.database.cakes.CakeEntity;
import com.zorkoalex.shop.database.orders.OrderEntity;

public interface PurchaseService {
    void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer count);
}

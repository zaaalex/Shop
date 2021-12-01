package com.zorkoalex.shop.orders.purchase;

import com.zorkoalex.shop.goods.CakeEntity;
import com.zorkoalex.shop.orders.order.OrderEntity;

public interface PurchaseService {
    void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer count);
}

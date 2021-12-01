package com.zorkoalex.shop.orders.order;

import com.zorkoalex.shop.dto.Order;
import com.zorkoalex.shop.dto.Orders;

public interface OrderService {
   void addOrder(Order order);
   Orders getOrders();
}


package com.zorkoalex.shop.orders.order;

import com.zorkoalex.shop.dto.order.Order;
import com.zorkoalex.shop.dto.order.Orders;
import com.zorkoalex.shop.exception.OrderNotFoundException;
import com.zorkoalex.shop.orders.OrderStatus;

public interface OrderService {
   void addOrder(Order order);
   Orders getOrders();
   void changeOrder (Long id, OrderStatus orderStatus);
   void deleteOrder (Long id) throws OrderNotFoundException;
}


package com.zorkoalex.shop.orders.order;

import com.zorkoalex.shop.dto.Order;
import com.zorkoalex.shop.dto.Orders;
import com.zorkoalex.shop.exception.OrderNotFoundException;

public interface OrderService {
   void addOrder(Order order);
   Orders getOrders();
   void changeOrder (Order order);
   void deleteOrder (Long id) throws OrderNotFoundException;
}


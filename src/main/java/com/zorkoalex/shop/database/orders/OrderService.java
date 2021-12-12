package com.zorkoalex.shop.database.orders;

import com.zorkoalex.shop.dto.order.Order;
import com.zorkoalex.shop.dto.order.Orders;
import com.zorkoalex.shop.dto.order.Purchase;
import com.zorkoalex.shop.exception.OrderNotFoundException;
import com.zorkoalex.shop.database.OrderStatus;

public interface OrderService {
   void addOrder(Order order);
   void changeOrderStatus (Long id, OrderStatus orderStatus);
   void addPurchaseInList (Long id, Purchase purhase);
   void deletePurchaseInList (Long id, Purchase purhase);
   void deleteOrder (Long id) throws OrderNotFoundException;
   Orders getOrders();
   Orders getOrder (String number);
}


package com.zorkoalex.shop.orders;

import com.zorkoalex.shop.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
   boolean existsByUser(User user);
   boolean existsByPayment(Payment payment);
   boolean existsByOrderStatus(OrderStatus orderStatus);

   void findOrderEntityByUser(User user);
}

package com.zorkoalex.shop.orders.payment;

import com.zorkoalex.shop.dto.Payment;
import com.zorkoalex.shop.orders.PaymentStatus;
import com.zorkoalex.shop.orders.order.OrderEntity;

import java.time.LocalDateTime;

public interface PaymentService {
    void addPayment(OrderEntity orderEntity, Double amount, LocalDateTime paymentDate, PaymentStatus status);
}

package com.zorkoalex.shop.orders.payment;

import com.zorkoalex.shop.orders.PaymentStatus;
import com.zorkoalex.shop.orders.order.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {this.paymentRepository=paymentRepository;}

    @Override
    public void addPayment(OrderEntity orderEntity, Double amount, LocalDateTime paymentDate, PaymentStatus status) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setOrder(orderEntity);
        paymentEntity.setAmount(amount);
        paymentEntity.setPaymentDate(paymentDate);
        paymentEntity.setStatus(status);
        paymentRepository.saveAndFlush(paymentEntity);
    }
}

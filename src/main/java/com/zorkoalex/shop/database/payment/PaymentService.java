package com.zorkoalex.shop.database.payment;

import com.zorkoalex.shop.database.PaymentStatus;

public interface PaymentService {
    void changePaymentStatus(Long id, PaymentStatus paymentStatus);
}

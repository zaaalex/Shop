package com.zorkoalex.shop.database.payment;

import com.zorkoalex.shop.database.PaymentStatus;
import com.zorkoalex.shop.exception.PaymentNotFoundException;

public interface PaymentService {
    void changePaymentStatus(Long id, PaymentStatus paymentStatus) throws PaymentNotFoundException;
}

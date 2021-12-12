package com.zorkoalex.shop.database.payment;

import com.zorkoalex.shop.exception.PaymentNotFoundException;
import com.zorkoalex.shop.database.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {this.paymentRepository=paymentRepository;}

    @Override
    public void changePaymentStatus(Long id, PaymentStatus paymentStatus) throws PaymentNotFoundException {
        if(!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment with ID "+id+ " doesn't exist");
        }
        else  {
            PaymentEntity paymentEntity = paymentRepository.getById(id);
            paymentEntity.setStatus(paymentStatus);
            paymentRepository.saveAndFlush(paymentEntity);
        }
    }
}

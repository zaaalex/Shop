package com.zorkoalex.shop.orders.payment;

import com.zorkoalex.shop.orders.PaymentStatus;
import com.zorkoalex.shop.orders.order.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "PAYMENTINFO")
public class PaymentEntity {
    @Setter(AccessLevel.NONE)
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter(AccessLevel.PUBLIC)
    private Double amount;

    @Setter(AccessLevel.PUBLIC)
    private LocalDateTime paymentDate;

    @Setter(AccessLevel.PUBLIC)
    private PaymentStatus status;

    @Setter(AccessLevel.PUBLIC)
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private OrderEntity order;
}

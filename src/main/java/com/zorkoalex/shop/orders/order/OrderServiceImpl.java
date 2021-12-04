package com.zorkoalex.shop.orders.order;

import com.zorkoalex.shop.dto.*;
import com.zorkoalex.shop.goods.CakeRepository;
import com.zorkoalex.shop.orders.payment.PaymentEntity;
import com.zorkoalex.shop.orders.purchase.PurchaseEntity;
import com.zorkoalex.shop.users.UserEntity;
import com.zorkoalex.shop.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CakeRepository cakeRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CakeRepository cakeRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cakeRepository = cakeRepository;
    }

    @Override
    public void addOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDelivery(order.getDelivery());
        orderEntity.setDeliveryAddress(order.getDeliveryAddress());
        orderEntity.setDeliveryTime(order.getDeliveryTime());

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentDate(order.getPayment().getPaymentDate());
        paymentEntity.setAmount(order.getPayment().getAmount());
        paymentEntity.setStatus(order.getPayment().getStatus());
        paymentEntity.setOrder(orderEntity);
        orderEntity.setPayment(paymentEntity);

        orderEntity.setOrderStatus(order.getOrderStatus());

        orderEntity.setPurchases(order.getPurchases().stream()
                .map(purchase -> {
                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                    purchaseEntity.setCount(purchase.getCount());
                    purchaseEntity.setOrder(orderEntity);
                    purchaseEntity.setCake(cakeRepository.findById(purchase.getCakeId()).orElseThrow(RuntimeException::new));
                    return purchaseEntity;
                }).collect(Collectors.toList()));
        orderEntity.setUser(userRepository.findUserEntityByNumber(order.getUser().getNumber()));
        orderRepository.saveAndFlush(orderEntity);
    }


    @Override
    public Orders getOrders() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        List<Order> orderList = orderEntityList.stream().map(c -> {
            Order order = new Order();

            UserEntity userEntity = c.getUser();
            User user = new User();
            user.setName(userEntity.getName());
            user.setNumber(userEntity.getNumber());
            order.setUser(user);

            order.setDelivery(c.getDelivery());
            order.setDeliveryAddress(c.getDeliveryAddress());
            order.setDeliveryTime(c.getDeliveryTime());

            PaymentEntity paymentEntity=c.getPayment();
            Payment payment = new Payment();
            payment.setPaymentDate(paymentEntity.getPaymentDate());
            payment.setStatus(paymentEntity.getStatus());
            payment.setAmount(paymentEntity.getAmount());
            order.setPayment(payment);

            order.setOrderStatus(c.getOrderStatus());

            order.setPurchases(c.getPurchases().stream()
                    .map(purchaseEntity -> {
                        Purchase purchase = new Purchase();
                        purchase.setCount(purchaseEntity.getCount());
                        purchase.setCakeId(purchaseEntity.getCake().getId());
                        return purchase;
                    }).collect(Collectors.toList()));


            return order;
        }).collect(Collectors.toList());
        Orders orders = new Orders();
        orders.setOrderList(orderList);
        return orders;
    }
}

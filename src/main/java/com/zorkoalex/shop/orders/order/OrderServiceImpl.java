package com.zorkoalex.shop.orders.order;

import com.zorkoalex.shop.dto.*;
import com.zorkoalex.shop.exception.OrderNotFoundException;
import com.zorkoalex.shop.goods.CakeEntity;
import com.zorkoalex.shop.goods.CakeRepository;
import com.zorkoalex.shop.orders.OrderStatus;
import com.zorkoalex.shop.orders.payment.PaymentEntity;
import com.zorkoalex.shop.orders.purchase.PurchaseEntity;
import com.zorkoalex.shop.users.UserEntity;
import com.zorkoalex.shop.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
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
            order.setId(c.getId());

            UserEntity userEntity = c.getUser();
            User user = new User();
            user.setName(userEntity.getName());
            user.setNumber(userEntity.getNumber());
            order.setUser(user);

            order.setDelivery(c.getDelivery());
            order.setDeliveryAddress(c.getDeliveryAddress());
            order.setDeliveryTime(c.getDeliveryTime());
            order.setOrderStatus(c.getOrderStatus());

            order.setPurchases(c.getPurchases().stream()
                    .map(purchaseEntity -> {
                        Purchase purchase = new Purchase();
                        purchase.setCount(purchaseEntity.getCount());
                        purchase.setCakeId(purchaseEntity.getCake().getId());
                        return purchase;
                    }).collect(Collectors.toList()));

            Double amount=0.0;
            for (Purchase el: order.getPurchases()){
                amount+=el.getCount()*cakeRepository.findCakeEntityById(el.getCakeId()).getPrise();
            }

            PaymentEntity paymentEntity=c.getPayment();
            Payment payment = new Payment();
            payment.setPaymentDate(paymentEntity.getPaymentDate());
            payment.setStatus(paymentEntity.getStatus());
            payment.setAmount(amount);
            order.setPayment(payment);

            return order;
        }).collect(Collectors.toList());
        Orders orders = new Orders();
        orders.setOrderList(orderList);
        return orders;
    }

    @Override
    public void changeOrder(Long id, OrderStatus orderStatus) throws OrderNotFoundException {
        if(!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID "+id+ " doesn't exist");
        }
        else  {
            OrderEntity orderEntity = orderRepository.getById(id);
            orderEntity.setOrderStatus(orderStatus);
            orderRepository.saveAndFlush(orderEntity);
        }
    }

    @Override
    public void deleteOrder(Long el) throws OrderNotFoundException {
        if(!orderRepository.existsById(el)) {
            throw new OrderNotFoundException("Order with ID "+el+ " doesn't exist");
        }
        else orderRepository.deleteById(el);
    }
}

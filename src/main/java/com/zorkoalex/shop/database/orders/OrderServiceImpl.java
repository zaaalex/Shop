package com.zorkoalex.shop.database.orders;

import com.zorkoalex.shop.dto.order.Order;
import com.zorkoalex.shop.dto.order.Orders;
import com.zorkoalex.shop.dto.order.Payment;
import com.zorkoalex.shop.dto.order.Purchase;
import com.zorkoalex.shop.dto.user.User;
import com.zorkoalex.shop.exception.OrderNotFoundException;
import com.zorkoalex.shop.database.cakes.CakeRepository;
import com.zorkoalex.shop.database.OrderStatus;
import com.zorkoalex.shop.database.payment.PaymentEntity;
import com.zorkoalex.shop.database.purchase.PurchaseEntity;
import com.zorkoalex.shop.database.purchase.PurchaseRepository;
import com.zorkoalex.shop.database.users.UserEntity;
import com.zorkoalex.shop.database.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CakeRepository cakeRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CakeRepository cakeRepository, PurchaseRepository purchaseRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cakeRepository = cakeRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void addOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setDelivery(order.getDelivery());
        orderEntity.setDeliveryAddress(order.getDeliveryAddress());
        orderEntity.setDeliveryTime(order.getDeliveryTime());
        PaymentEntity paymentEntity = new PaymentEntity();

        //paymentEntity.setPaymentDate(order.getPayment().getPaymentDate());
        paymentEntity.setAmount(0.0);
        paymentEntity.setStatus(order.getPayment().getStatus());
        paymentEntity.setOrder(orderEntity);
        orderEntity.setPayment(paymentEntity);

        orderEntity.setOrderStatus(OrderStatus.NEW);

        /*orderEntity.setPurchases(order.getPurchases().stream()
                .map(purchase -> {
                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                    purchaseEntity.setCount(purchase.getCount());
                    purchaseEntity.setOrder(orderEntity);
                    purchaseEntity.setCake(cakeRepository.findById(purchase.getCakeId()).orElseThrow(RuntimeException::new));
                    return purchaseEntity;
                }).collect(Collectors.toList()));*/
        List<PurchaseEntity> l= new ArrayList<>();
        orderEntity.setPurchases(l);
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
                amount+=el.getCount()*cakeRepository.findCakeee(el.getCakeId()).getPrice();
            }

            PaymentEntity paymentEntity=c.getPayment();
            Payment payment = new Payment();
            payment.setId(paymentEntity.getId());
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
    public Orders getOrder(String number) throws OrderNotFoundException{
        if (!orderRepository.existsByUser_Number(number))
            throw new OrderNotFoundException("Order with USER NUMBER "+number+ " doesn't exist");
        else {
            List<OrderEntity> orderEntityList = orderRepository.findOrderEntitiesByUser_Numberrr(number);
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

                Double amount = 0.0;
                for (Purchase el : order.getPurchases()) {
                    amount += el.getCount() * cakeRepository.findCakeee(el.getCakeId()).getPrice();
                }

                PaymentEntity paymentEntity = c.getPayment();
                Payment payment = new Payment();
                payment.setStatus(paymentEntity.getStatus());
                payment.setAmount(amount);
                order.setPayment(payment);

                return order;
            }).collect(Collectors.toList());
            Orders orders = new Orders();
            orders.setOrderList(orderList);
            return orders;
        }
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus orderStatus) throws OrderNotFoundException {
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
    public void addPurchaseInList(Long id, Purchase newpurchase) throws OrderNotFoundException {
        if(!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID "+id+ " doesn't exist");
        }
        else  {
            OrderEntity orderEntity = orderRepository.getById(id);
            orderEntity.getPayment().setAmount(orderEntity.getPayment().getAmount()+
                    cakeRepository.findCakeee(newpurchase.getCakeId()).getPrice()*newpurchase.getCount());
            boolean success = false;
            for (PurchaseEntity el: orderEntity.getPurchases()){
                if (el.getCake().getId().equals(newpurchase.getCakeId())) {
                    el.setCount(newpurchase.getCount()+el.getCount());
                    purchaseRepository.saveAndFlush(el);
                    success = true;
                }
            }

            if(!success) {
                PurchaseEntity purchaseEntity = new PurchaseEntity();
                purchaseEntity.setCount(newpurchase.getCount());
                purchaseEntity.setOrder(orderEntity);
                purchaseEntity.setCake(cakeRepository.findById(newpurchase.getCakeId()).orElseThrow(RuntimeException::new));

                orderEntity.getPurchases().add(purchaseEntity);
                orderRepository.saveAndFlush(orderEntity);
            }
        }
    }

    @Override
    public void deletePurchaseInList(Long id, Purchase newpurchase) throws OrderNotFoundException {
        if(!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID "+id+ " doesn't exist");
        }
        else  {
            OrderEntity orderEntity = orderRepository.getById(id);
            for (PurchaseEntity el: orderEntity.getPurchases()){
                if (el.getCake().getId().equals(newpurchase.getCakeId()))
                    purchaseRepository.deleteById(el.getId());
            }
        }
    }

   /* @Override
    public void deletePurchaseInList(Long id, Purchase newpurchase) throws OrderNotFoundException {
        if(!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with ID "+id+ " doesn't exist");
        }
        else  {
            int index=-1;
            OrderEntity orderEntity = orderRepository.getById(id);

            for (PurchaseEntity el: orderEntity.getPurchases()){
                index++;
                if (el.getCake().getId().equals(newpurchase.getCakeId())) {
                    List <PurchaseEntity> purchaseEntityListList = orderEntity.getPurchases();
                    purchaseEntityListList.remove(index);
                    orderEntity.setPurchases(purchaseEntityListList);
                    orderRepository.saveAndFlush(orderEntity);
                    return;
                }
            }
        }
    }*/

    @Override
    public void deleteOrder(Long el) throws OrderNotFoundException {
        if(!orderRepository.existsById(el)) {
            throw new OrderNotFoundException("Order with ID "+el+ " doesn't exist");
        }
        else orderRepository.deleteById(el);
    }
}

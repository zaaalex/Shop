package com.zorkoalex.shop.database.purchase;

import com.zorkoalex.shop.database.cakes.CakeEntity;
import com.zorkoalex.shop.database.orders.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl (PurchaseRepository purchaseRepository) {this.purchaseRepository=purchaseRepository;}

    @Override
    public void addPurchase(OrderEntity orderEntity, CakeEntity cakeEntity, Integer count) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setCount(count);
        purchase.setOrder(orderEntity);
        purchase.setCake(cakeEntity);
        purchaseRepository.saveAndFlush(purchase);
    }
}

package com.zorkoalex.shop.orders;

import com.zorkoalex.shop.dto.Purchase;
import com.zorkoalex.shop.goods.CakeRepository;
import com.zorkoalex.shop.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl (PurchaseRepository purchaseRepository) {this.purchaseRepository=purchaseRepository;}

    /*@Override
    public void addPurchase(Purchase purchase){


    };*/
}

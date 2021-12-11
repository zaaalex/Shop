package com.zorkoalex.shop.goods;

import com.zorkoalex.shop.dto.cake.Cake;
import com.zorkoalex.shop.dto.cake.Cakes;
import com.zorkoalex.shop.exception.CakeNotFoundException;

//прослойка с бизнес-логикой
public interface CakesService {
    Cakes getCakes();
    Cake getCake(Long id);
    void addCake(Cake cake);
    void deleteCake (Long id) throws CakeNotFoundException;
}

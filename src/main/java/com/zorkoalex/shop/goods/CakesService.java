package com.zorkoalex.shop.goods;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Cakes;
import com.zorkoalex.shop.exception.CakeNotFoundException;

import java.util.List;

//прослойка с бизнес-логикой
public interface CakesService {
    Cakes getCakes();
    Cake getCake(Long id);
    void addCake(Cake cake);
    void deleteCake (Long id) throws CakeNotFoundException;
}

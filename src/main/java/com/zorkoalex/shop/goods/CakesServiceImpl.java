package com.zorkoalex.shop.goods;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Cakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakesServiceImpl implements CakesService{
    private final CakeRepository cakeRepository;

    @Autowired
    public CakesServiceImpl(CakeRepository cakeRepository){
        this.cakeRepository=cakeRepository;
    }

    @Override
    public Cakes getCakes(){
        List<CakeEntity> cakeEntityList = cakeRepository.findAll();
        List <Cake> cakeList = cakeEntityList.stream().map(c->{
            Cake cake=new Cake();
            cake.setId(c.getId());
            cake.setName(c.getName());
            cake.setImage(c.getImage());
            cake.setPrise(c.getPrise());
            cake.setWeight(c.getWeight());
            cake.setCalories(c.getCalories());
            return cake;
        }).collect(Collectors.toList());
        Cakes cakes=new Cakes();
        cakes.setCakeList(cakeList);
        return cakes;
    }
}

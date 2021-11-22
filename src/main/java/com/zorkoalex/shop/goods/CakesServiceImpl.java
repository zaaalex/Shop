package com.zorkoalex.shop.goods;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Cakes;
import com.zorkoalex.shop.exception.CakeNotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakesServiceImpl implements CakesService {
    private final CakeRepository cakeRepository;

    @Autowired
    public CakesServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public Cakes getCakes() {
        List<CakeEntity> cakeEntityList = cakeRepository.findAll();
        List<Cake> cakeList = cakeEntityList.stream().map(c -> {
            Cake cake = new Cake();
            cake.setId(c.getId());
            cake.setName(c.getName());
            cake.setImage(c.getImage());
            cake.setPrise(c.getPrise());
            cake.setWeight(c.getWeight());
            cake.setCalories(c.getCalories());
            return cake;
        }).collect(Collectors.toList());
        Cakes cakes = new Cakes();
        cakes.setCakeList(cakeList);
        return cakes;
    }

    @Override
    public Cake getCake(Long id) {
        CakeEntity cakeEntity = cakeRepository.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CakeNotFoundException("No such cake"));

        Cake cake = new Cake();
        cake.setId(cakeEntity.getId());
        cake.setName(cakeEntity.getName());
        cake.setImage(cakeEntity.getImage());
        cake.setPrise(cakeEntity.getPrise());
        cake.setWeight(cakeEntity.getWeight());
        cake.setCalories(cakeEntity.getCalories());
        return cake;
    }


    @Override
    public void addCake(Cake cake) {
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setName(cake.getName());
        cakeEntity.setImage(cake.getImage());
        cakeEntity.setPrise(cake.getPrise());
        cakeEntity.setWeight(cake.getWeight());
        cakeEntity.setCalories(cake.getCalories());
        cakeRepository.saveAndFlush(cakeEntity);
    }

    @Override
    public void deleteCake(List <Long> id) {
       for (Long el: id){
           if (cakeRepository.existsById(el)) cakeRepository.deleteById(el);
       }
    }
}

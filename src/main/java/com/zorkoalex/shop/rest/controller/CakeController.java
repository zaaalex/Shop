package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Cakes;
import com.zorkoalex.shop.exception.CakeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
//@RequestMapping("v1/cakes")
public class CakeController {
    public Cakes cakeList = new Cakes();

    public CakeController() {
        Cake cake1 = new Cake();
        cake1.setId(1L);
        cake1.setName("Napoleon");
        cake1.setPrise(new BigDecimal(100));
        cake1.setWeight(new BigDecimal(100));
        cake1.setCalories(new BigDecimal(100));
        cake1.setImage("cake1.jpg");

        Cake cake2 = new Cake();
        cake2.setId(2L);
        cake2.setName("Rose");
        cake2.setPrise(new BigDecimal(200));
        cake2.setWeight(new BigDecimal(200));
        cake2.setCalories(new BigDecimal(200));
        cake2.setImage("cake1.jpg");

        List<Cake> tmp = new ArrayList<Cake>();
        tmp.add(cake1);
        tmp.add(cake2);
        cakeList.setCakeList(tmp);
    }

    @GetMapping(value="cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes(){
        return cakeList;
    }

    @GetMapping(value="cake/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id){
        return cakeList.getCakeList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new CakeNotFoundException("No such cake"));
    }
    @PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Cake newCake) {

    }
}

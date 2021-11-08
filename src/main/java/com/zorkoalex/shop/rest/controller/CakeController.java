package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Cakes;
import com.zorkoalex.shop.exception.CakeNotFoundException;
import com.zorkoalex.shop.goods.CakesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
//@RequestMapping("v1/cakes")
public class CakeController {
    private Cakes cakeList = new Cakes();
    private static long idCounter = 0;
    private final CakesServiceImpl cakesService;

    @Autowired
    public CakeController(CakesServiceImpl cakesService) {
    this.cakesService =cakesService;
        List<Cake> tmp = new ArrayList<Cake>();
        cakeList.setCakeList(tmp);
    }

//    public CakeController() {
//        Cake cake1 = new Cake();
//        cake1.setId(idCounter);
//        idCounter++;
//        cake1.setName("Napoleon");
//        cake1.setPrise(new BigDecimal(100));
//        cake1.setWeight(new BigDecimal(100));
//        cake1.setCalories(new BigDecimal(100));
//        cake1.setImage("cake1.jpg");
//
//        Cake cake2 = new Cake();
//        cake2.setId(idCounter);
//        idCounter++;
//        cake2.setName("Rose");
//        cake2.setPrise(new BigDecimal(200));
//        cake2.setWeight(new BigDecimal(200));
//        cake2.setCalories(new BigDecimal(200));
//        cake2.setImage("cake1.jpg");
//
//        List<Cake> tmp = new ArrayList<Cake>();
//        tmp.add(cake1);
//        tmp.add(cake2);
//        cakeList.setCakeList(tmp);
//    }

    @GetMapping(value="cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes(){
        return cakesService.getCakes();
    }

    @GetMapping(value="cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id){
        return cakeList.getCakeList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new CakeNotFoundException("No such cake"));
    }

    static public Long compareTo(Cake el) {
      return el.getId();
    }

    @PostMapping(path = "cakes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> createCake(@RequestBody @Validated Cake newCake){
            newCake.setId(idCounter);
            idCounter++;
            cakeList.getCakeList().add(newCake);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.Cake;
import com.zorkoalex.shop.dto.Orders;
import com.zorkoalex.shop.exception.CakeNotFoundException;
import com.zorkoalex.shop.goods.CakesService;
import com.zorkoalex.shop.orders.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final CakesService cakesService;

    @Autowired
    public AdminController(OrderService orderService, CakesService cakesService) {
        this.orderService = orderService;
        this.cakesService = cakesService;
    }

    @ResponseStatus(code=HttpStatus.CREATED)
    @PostMapping(path = "addCake", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCake(@RequestBody @Valid Cake newCake){
        cakesService.addCake(newCake);
    }

    @PostMapping(value="deleteCake", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> deleteCake(@RequestBody @Valid List<Long> id){
        try {
            cakesService.deleteCake(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (CakeNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Orders orders(){return orderService.getOrders();}
}

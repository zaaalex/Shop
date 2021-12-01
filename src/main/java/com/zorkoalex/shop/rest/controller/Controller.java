package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.*;
import com.zorkoalex.shop.exception.CakeNotFoundException;
import com.zorkoalex.shop.exception.UserExistException;
import com.zorkoalex.shop.goods.CakesService;
import com.zorkoalex.shop.orders.order.OrderService;
import com.zorkoalex.shop.users.UserService;
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
public class Controller {
    private final CakesService cakesService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public Controller(CakesService cakesService, UserService userService, OrderService orderService) {
        this.cakesService =cakesService;
        this.userService=userService;
        this.orderService=orderService;
    }

    @GetMapping(value="cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes(){return cakesService.getCakes();}

    @GetMapping(value="orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Orders orders(){return orderService.getOrders();}

    @GetMapping(value="cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id){
        return cakesService.getCake(id);
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

    @ResponseStatus(code=HttpStatus.CREATED)
    @PostMapping(path = "addCake", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCake(@RequestBody @Valid Cake newCake){
        cakesService.addCake(newCake);
    }

    @ResponseStatus(code=HttpStatus.CREATED)
    @PostMapping(path = "addOrder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody @Valid Order newOrder){
        try {
            userService.addUser(newOrder.getUser());
        }
        catch (UserExistException ignored){
        }
        orderService.addOrder(newOrder);
    }

    @ResponseStatus(code=HttpStatus.CREATED)
    @PostMapping(path = "addUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody @Valid User newUser){
        try {
            userService.addUser(newUser);
        }
        catch (UserExistException ignored){
        }
    }
}

package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.cake.Cake;
import com.zorkoalex.shop.dto.cake.Cakes;
import com.zorkoalex.shop.dto.order.Order;
import com.zorkoalex.shop.dto.order.Orders;
import com.zorkoalex.shop.dto.user.User;
import com.zorkoalex.shop.dto.user.Users;
import com.zorkoalex.shop.exception.UserExistException;
import com.zorkoalex.shop.database.cakes.CakesService;
import com.zorkoalex.shop.database.orders.OrderService;
import com.zorkoalex.shop.database.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    private final CakesService cakesService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserController(CakesService cakesService, UserService userService, OrderService orderService) {
        this.cakesService =cakesService;
        this.userService=userService;
        this.orderService=orderService;
    }

    @GetMapping(value="cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes(){
        return cakesService.getCakes();
    }

    @GetMapping(value="users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users users(){
        return userService.getUsers();
    }

    @GetMapping(value="order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Orders orders(@PathVariable String number){
        return orderService.getOrder(number);
    }

    @GetMapping(value="cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id){
        return cakesService.getCake(id);
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

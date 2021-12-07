package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.*;
import com.zorkoalex.shop.goods.CakesService;
import com.zorkoalex.shop.orders.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final CakesService cakesService;

    @Autowired
    public AdminController(OrderService orderService, CakesService cakesService) {
        this.orderService = orderService;
        this.cakesService = cakesService;
    }

    @GetMapping(value="home")
    public String home(){
        return "home";
    }

    @GetMapping(value="cakes")
    public String cakes(Model model){
        model.addAttribute("cakes",  cakesService.getCakes().getCakeList());
        model.addAttribute("cake", new Cake());
        return "cakes";
    }

    @PostMapping(value = "addCake")
    public String createCake(Cake newCake){
        cakesService.addCake(newCake);
        return "redirect:/admin/cakes";
    }

    @PostMapping(value="deleteCake")
    public String deleteCake(Cake cake){
        cakesService.deleteCake(cake.getId());
        return "redirect:/admin/cakes";
    }

    @PostMapping(value = "changeOrder/{id}")
    public String changeOrder(@PathVariable Long id, Order order){
        orderService.changeOrder(id, order.getOrderStatus());
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }

    @GetMapping(value="orders")
    public String orders(Model model){
        model.addAttribute("orders", orderService.getOrders().getOrderList());
        model.addAttribute("order", new Order());
        model.addAttribute("status", new Order());
        return "orders";
    }
}

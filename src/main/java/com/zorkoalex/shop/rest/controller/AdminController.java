package com.zorkoalex.shop.rest.controller;

import com.zorkoalex.shop.dto.Who;
import com.zorkoalex.shop.dto.cake.Cake;
import com.zorkoalex.shop.dto.order.Order;
import com.zorkoalex.shop.dto.order.Payment;
import com.zorkoalex.shop.dto.order.Purchase;
import com.zorkoalex.shop.dto.user.User;
import com.zorkoalex.shop.exception.UserExistException;
import com.zorkoalex.shop.database.cakes.CakesService;
import com.zorkoalex.shop.database.orders.OrderService;
import com.zorkoalex.shop.database.payment.PaymentService;
import com.zorkoalex.shop.database.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final CakesService cakesService;
    private final UserService userService;
    private final PaymentService paymentService;

    @Autowired
    public AdminController(OrderService orderService, CakesService cakesService, UserService userService, PaymentService paymentService) {
        this.orderService = orderService;
        this.cakesService = cakesService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @GetMapping(value="home")
    public String home(Model model, Principal principal){
        Who who = new Who(principal.getName());
        model.addAttribute("who", who);
        return "home";
    }

    @GetMapping(value="cakes")
    public String cakes(Model model){
        model.addAttribute("cakes",  cakesService.getCakes().getCakeList());
        model.addAttribute("cake", new Cake());
        return "cakes";
    }

    @PostMapping(path = "addOrder")
    public String createOrder(Order newOrder){
        try {
            userService.addUser(newOrder.getUser());
        }
        catch (UserExistException ignored){
        }
        orderService.addOrder(newOrder);
        return "redirect:/admin/orders";
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

    @PostMapping(value = "deleteUser/{number}")
    public String deleteUser(@PathVariable String number){
        try {
            userService.deleteUser(number);
        }
        catch (UserExistException ignored){
        }
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "changeOrderStatus/{id}")
    public String changeOrderStatus(@PathVariable Long id, Order order){
        orderService.changeOrderStatus(id, order.getOrderStatus());
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "changePaymentStatus/{id}")
    public String changePaymentStatus(@PathVariable Long id, Payment payment){
       paymentService.changePaymentStatus(id, payment.getStatus());
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "addPurchaseInList/{id}")
    public String addPurchaseInList(@PathVariable Long id, Purchase purchase){
        orderService.addPurchaseInList(id, purchase);
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "deletePurchaseInList/{id}")
    public String deletePurchaseInList(@PathVariable Long id, Purchase purchase){
        orderService.deletePurchaseInList(id, purchase);
        return "redirect:/admin/orders";
    }

    @PostMapping(value = "deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/admin/orders";
    }

    @GetMapping(value="orders")
    public String orders(Model model, Principal principal){
        model.addAttribute("orders", orderService.getOrders().getOrderList());
        model.addAttribute("users",  userService.getUsers().getUserList());
        model.addAttribute("order", new Order());
        model.addAttribute("neworder", new Order());
        model.addAttribute("status", new Order());
        model.addAttribute("user", new User());
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("payment", new Payment());
        return "orders";
    }
}

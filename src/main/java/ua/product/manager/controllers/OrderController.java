package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.models.Order;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.OrderService;
import ua.product.manager.services.interfaces.UserService;

import java.security.Principal;

@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    private void setServices(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrdersPage() {
        return "user-views/my-orders";
    }

    @RequestMapping(value = "/order/form", method = RequestMethod.GET)
    public ModelAndView createOrder(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByUsername(principal.getName());
        modelAndView.addObject("order", orderService.getNewOrder());
        modelAndView.addObject("currentUserId", user.getId());
        modelAndView.setViewName("user-views/order");
        return modelAndView;
    }

    @RequestMapping(value = "/orders/process", method = RequestMethod.POST)
    public String processOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts() {
        return "admin-views/products";
    }

}

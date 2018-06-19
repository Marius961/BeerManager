package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;
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


    @RequestMapping(value = "/{username}/orders", method = RequestMethod.GET)
    public ModelAndView getOrders(@PathVariable String username, Principal principal) {
        if (username.equals(principal.getName())) {
            ModelAndView modelAndView = new ModelAndView();
            User user = userService.getUserByUsername(username);
            modelAndView.addObject("orders", orderService.getAllOrdersByUserId(user.getId()));
            modelAndView.addObject("currentUserName", username);
            modelAndView.setViewName("orders");
            return modelAndView;
        }
        return null;
    }

    @RequestMapping(value = "/{username}/orders/create", method = RequestMethod.GET)
    public ModelAndView createOrder(@PathVariable String username, Principal principal) {
        if (username.equals(principal.getName())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("order", orderService.getNewOrder());
            modelAndView.addObject("currentUser", userService.getUserByUsername(username));
            modelAndView.setViewName("order");
            return modelAndView;
        }
        return null;
    }

    @RequestMapping(value = "/{username}/orders/process", method = RequestMethod.POST)
    public String processOrder(@ModelAttribute Order order, @PathVariable String username) {
        orderService.createOrder(order);
        return "redirect:/" + username + "/orders";
    }

    @RequestMapping(value = "/orders/remove/{orderId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeOrder(@PathVariable int orderId) {
        orderService.removeOrder(orderId);
    }
}

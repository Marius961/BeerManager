package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
            modelAndView.addObject("currentUser", username);
            modelAndView.setViewName("orders");
            return modelAndView;
        }
        return null;
    }
}

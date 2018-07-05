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
public class AdminController {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    private void setUserService(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsersList() {
        return "users";
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUserById(id));
        modelAndView.setViewName("userData");
        return modelAndView;
    }

    @RequestMapping(value = "/all-orders", method = RequestMethod.GET)
    public String getAllOrdersList() {
        return "all-orders";
    }
}


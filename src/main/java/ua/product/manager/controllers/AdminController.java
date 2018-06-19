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

    @RequestMapping(value = {"/{username}/users/list", "/{username}/users"}, method = RequestMethod.GET)
    public ModelAndView getUsersList(Principal principal, @PathVariable String username) {
        if (principal.getName().equals(username)) {
            ModelAndView modelAndView = new ModelAndView();
            User currentUser = userService.getUserByUsername(principal.getName());
            modelAndView.addObject("users", userService.getUsersList());
            modelAndView.addObject("currentUserId", currentUser.getId());
            modelAndView.addObject("currentUserName", username);
            modelAndView.setViewName("users");
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/{username}/users/get/{id}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable int id, @PathVariable String username, Principal principal) {
        if (principal.getName().equals(username)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", userService.getUserById(id));
            modelAndView.addObject("currentUserName", username);
            modelAndView.addObject("currentUserId", id);
            modelAndView.addObject("orders", orderService.getAllOrdersByUserId(id));
            modelAndView.setViewName("userData");
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }
}


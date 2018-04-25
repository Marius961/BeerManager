package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

import java.security.Principal;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users/list", method = RequestMethod.GET)
    public ModelAndView getUsersList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        User currentUser = userService.getUserByUsername(principal.getName());
        modelAndView.addObject("users", userService.getUsersList());
        modelAndView.addObject("currentUserId", currentUser.getId());
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(value = "/users/get/{id}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable int id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        String currentUserName = principal.getName();
        modelAndView.addObject("user", userService.getUserById(id));
        modelAndView.addObject("currentUserName", currentUserName);
        modelAndView.addObject("currentUserId", id);
        modelAndView.setViewName("userData");
        return modelAndView;
    }
}


package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.services.interfaces.UserService;

import java.security.Principal;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/home" , "/index"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }

}

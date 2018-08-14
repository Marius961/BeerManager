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
        String role = userService.getUserRole();
        if (role != null) {
            if (role.equals("ROLE_USER"))
                return "redirect:/orders";
            if (role.equals("ROLE_ADMIN"))
                return "redirect:/users";
        }
        return "all-views/index";
    }

    @RequestMapping(value = "/my-profile", method = RequestMethod.GET)
    public ModelAndView getUserProfile(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userService.getUserByUsername(principal.getName()));
        modelAndView.setViewName("user-views/my-profile");
        return modelAndView;
    }
}

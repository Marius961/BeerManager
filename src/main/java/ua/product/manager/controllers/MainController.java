package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/home" , "/index" , "/{username}"}, method = RequestMethod.GET)
    public String getHomePage(@PathVariable(required = false) String username, Principal principal) {
        String role = userService.getUserRole();
        if (role != null) {
            if (username != null && principal.getName().equals(username)) {
                if (role.equals("ROLE_USER")) {
                    return "redirect:/" + username + "/orders";
                }
                if (role.equals("ROLE_ADMIN")) {
                    return "redirect:/" + username + "/users";
                }
            }
            if (role.equals("ROLE_USER")) return "redirect:/" + principal.getName() + "/orders";
            if (role.equals("ROLE_ADMIN")) return "redirect:/" + principal.getName() + "/users/list";
        }
        return "index";
    }

    @RequestMapping(value = "/{username}/my-profile", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@PathVariable String username, Principal principal) {
        if (username.equals(principal.getName())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", userService.getUserByUsername(username));
            modelAndView.setViewName("profile");
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }
}

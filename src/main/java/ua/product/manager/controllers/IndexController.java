package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String getHomePage() {
        return "/redirect:/registration";
    }
}

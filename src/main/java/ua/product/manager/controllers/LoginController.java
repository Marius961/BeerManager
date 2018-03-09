package ua.product.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.models.User;

@Controller
public class LoginController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String maim() {
        return "redirect:index";
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }




    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public String checkUser(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("user", user);
        return "main";
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed() {
        return new ModelAndView("login-failed", "message", "Login failed");
    }
}

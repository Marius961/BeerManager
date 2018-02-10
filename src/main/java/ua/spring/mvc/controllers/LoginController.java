package ua.spring.mvc.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.spring.mvc.models.User;

@Controller
public class LoginController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView maim() {
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("user") User user) {
        return new ModelAndView("main", "user", user);
    }
}

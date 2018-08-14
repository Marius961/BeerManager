package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.services.interfaces.UserService;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Properties;

@Controller
public class LoginController {

    private String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private String messagesPath = rootPath + "/locale/messages.properties";
    private Properties messages = new Properties();

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
        try {
            messages.load(new FileInputStream(messagesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "all-views/registration";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", messages.getProperty("loginOrPassword.invalid"));
        }
        modelAndView.setViewName("all-views/login");
        return modelAndView;
    }

}

package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;
import ua.product.manager.validators.TelNumValidator;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class RegisterController {

    @Autowired
    public RegisterController(UserService userService, TelNumValidator telNumValidator) {
        this.userService = userService;
        this.telNumValidator = telNumValidator;
    }

    @ModelAttribute
    public User createNewUser() {
        return new User();
    }

    private final UserService userService;
    private  final TelNumValidator telNumValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegPage() {
        return new ModelAndView("registration", "user", new User());
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public ModelAndView getRegPage(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        telNumValidator.validateWithCheckNum(user.getTelNumber(), bindingResult);
        ModelAndView modelAndView = new ModelAndView();
        if(!bindingResult.hasErrors()) {
            userService.registerUser(user);
            RedirectView redirectView = new RedirectView("/test");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
        } else modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.GET)
    public String regRedirect() {
        return "redirect:registration";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user");
        modelAndView.setViewName("test");
        return modelAndView;
    }
}

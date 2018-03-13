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
import ua.product.manager.models.UserData;
import ua.product.manager.services.interfaces.UserService;
import ua.product.manager.validators.TelNumValidator;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    public LoginController(UserService userService, TelNumValidator telNumValidator) {
        this.userService = userService;
        this.telNumValidator = telNumValidator;
    }

    @ModelAttribute
    public User createNewUser() {
        return new User();
    }

    @ModelAttribute
    public UserData createNewUserData() {
        return new UserData();
    }

    private final UserService userService;
    private  final TelNumValidator telNumValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView gerLoginPage() {
        return new ModelAndView("login1", "userData", new UserData());
    }



    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView getRegPage(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        telNumValidator.validate(userData.getTelNumber(), bindingResult);
        ModelAndView modelAndView = new ModelAndView();
        if(!bindingResult.hasErrors()) {
            User user = userService.checkAndGetUser(userData);
            if (user != null) {
                RedirectView redirectView = new RedirectView("/index");
                redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
                modelAndView.setView(redirectView);
            } else modelAndView.setViewName("login1");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.GET)
    public ModelAndView regRedirect() {
        return new ModelAndView("redirect:login1");
    }


}

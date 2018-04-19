package ua.product.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
//@SessionAttributes("user")
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            modelAndView.addObject("error", "Invalid usernameor password");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.addObject("message", user.getName() + " - у вас нет доступа");
        } else {
            modelAndView.addObject("message", "У вас нет доступа");
        }


        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }














//    @Autowired
//    public LoginController(UserService userService, TelNumValidator telNumValidator) {
//        this.userService = userService;
//        this.telNumValidator = telNumValidator;
//    }
//
//    @ModelAttribute
//    public User createNewUser() {
//        return new User();
//    }
//
//    @ModelAttribute
//    public UserData createNewUserData() {
//        return new UserData();
//    }
//
//    private final UserService userService;
//    private  final TelNumValidator telNumValidator;
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView gerLoginPage() {
//        return new ModelAndView("login1", "userData", new UserData());
//    }
//
//
//
//    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
//    public ModelAndView getRegPage(@Valid @ModelAttribute("userData") UserData userData, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        telNumValidator.validate(userData.getTelNumber(), bindingResult);
//        ModelAndView modelAndView = new ModelAndView();
//        if(!bindingResult.hasErrors()) {
//            User user = userService.checkAndGetUser(userData);
//            RedirectView redirectView = new RedirectView("index");
//            if (user != null) {
//                redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//                modelAndView.setView(redirectView);
//                redirectAttributes.addFlashAttribute("user", user);
//            } else {
//                redirectView.setUrl("login");
//                redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//                modelAndView.setView(redirectView);
//                redirectAttributes.addFlashAttribute("message", "Login failed");
//            }
//        } else modelAndView.setViewName("login1");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/check-user", method = RequestMethod.GET)
//    public ModelAndView regRedirect() {
//        return new ModelAndView("redirect:login1");
//    }
//

}

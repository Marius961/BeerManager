package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin.liveconnect.SecurityContextHelper;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

@Controller
@SessionAttributes("user")
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public User createNewUser() {
        return new User();
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String maim() {
        return "redirect:index";
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }



    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserPage() {
        printUserDetails();
        return "/content/user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "/content/admin";
    }


    private void printUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        for (GrantedAuthority auth : userDetails.getAuthorities()) {
            System.out.println(auth.getAuthority());
        }
    }












//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout() {
//        return new ModelAndView("index","user", new User());
//    }

}

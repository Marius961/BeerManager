package ua.product.manager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.product.manager.models.User;

@Controller
@SessionAttributes("user")
public class IndexController {

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
        return "/content/user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage() {
        return "/content/admin";
    }













//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout() {
//        return new ModelAndView("index","user", new User());
//    }

}

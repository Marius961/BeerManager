package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.containers.SimpleStringContainer;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;
import ua.product.manager.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
public class LoginRestController {

    private String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private String messagesPath = rootPath + "/locale/messages.properties";
    private Properties messages = new Properties();
    private UserValidator userValidator;

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
        try {
            messages.load(new FileInputStream(messagesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> registerUser(@RequestBody User user, BindingResult bindingResult, HttpServletRequest request) {
        if (user != null) {
            userValidator.validate(user, bindingResult);
            if (!bindingResult.hasErrors()) {
                userService.registerUser(request, user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/username-check/{username}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Boolean>> isUsernameAvailable(@PathVariable String username) {
        return new ResponseEntity<>(Collections.singletonMap("nameStatus", userService.checkUsername(username)), HttpStatus.OK);
    }

    @RequestMapping(value = "/email-check", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Boolean>> isUserEmailAvailable(@RequestBody SimpleStringContainer container) {
        return new ResponseEntity<>(Collections.singletonMap("emailStatus", userService.checkUserEmail(container.getStr())), HttpStatus.OK);
    }

    @RequestMapping(value = "/tel-check", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Boolean>> isUserTelAvailable(@RequestBody SimpleStringContainer container) {
        return new ResponseEntity<>(Collections.singletonMap("telStatus", userService.checkUserTel(container.getStr())), HttpStatus.OK);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getOrdersList() {
        List<User> users = userService.getUsersList();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = null;
        if (id != 0) {
            user = userService.getUserById(id);
        }
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
}
}

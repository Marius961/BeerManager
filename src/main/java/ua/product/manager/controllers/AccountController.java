package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.User;
import ua.product.manager.services.UserService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public User getAccountInfo() {
        return userService.getAccountIbfo();
    }

    @PostMapping
    public void updateAccountData(@RequestBody Map<String, String> data) {
        userService.updateUserDat(data.get("email"), data.get("firstName"));
    }

    @PostMapping("/password")
    public void updatePassword(@RequestBody Map<String, String> passwordData) {
        userService.changePassword(passwordData.get("oldPassword"), passwordData.get("newPassword"), passwordEncoder);
    }

    @PostMapping("/is-email-unique")
    public Map<String, Boolean> checkEmailRegistrationOnAnotherUser(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isValid", userService.checkEmailRegistrationOnAnotherUser(payload.get("email")));
    }
}

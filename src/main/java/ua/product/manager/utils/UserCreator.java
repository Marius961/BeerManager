package ua.product.manager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.product.manager.entities.Role;
import ua.product.manager.entities.User;
import ua.product.manager.repo.UserRepo;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class UserCreator {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCreator(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        addAdmin();


    }


    private void addAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@mail.com");
        admin.setFirstName("Admin");
        admin.setActive(true);
        admin.setRoles(new HashSet<>(Arrays.asList(Role.USER, Role.ADMIN)));
        userRepo.save(admin);
    }
}

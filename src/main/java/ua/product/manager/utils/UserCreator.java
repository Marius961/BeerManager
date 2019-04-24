package ua.product.manager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.product.manager.entities.Role;
import ua.product.manager.entities.ShippingAddress;
import ua.product.manager.entities.User;
import ua.product.manager.repo.ShippingAddressRepo;
import ua.product.manager.repo.UserRepo;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class UserCreator {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ShippingAddressRepo shippingAddressRepo;

    @Autowired
    public UserCreator(UserRepo userRepo, PasswordEncoder passwordEncoder, ShippingAddressRepo shippingAddressRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.shippingAddressRepo = shippingAddressRepo;
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

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setUser(admin);
        shippingAddress.setRecipientFullName("Admin Admin Admin");
        shippingAddress.setRecipientMobileNumber("0648484842");
        shippingAddress.setRegion("Lvivska obl.");
        shippingAddress.setCity("Lviv");
        shippingAddress.setStreet("Bohdana Hmelnitskogo");
        shippingAddress.setBuildingNumber("32 A");
        shippingAddress.setOfficeNumber("12");
        shippingAddress.setPostCode("79900");
        shippingAddressRepo.save(shippingAddress);
    }
}

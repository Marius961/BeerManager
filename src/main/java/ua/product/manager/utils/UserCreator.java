package ua.product.manager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.product.manager.entities.Status;
import ua.product.manager.entities.Role;
import ua.product.manager.entities.User;
import ua.product.manager.repo.StatusRepo;
import ua.product.manager.repo.ShippingAddressRepo;
import ua.product.manager.repo.UserRepo;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Component
public class UserCreator {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ShippingAddressRepo shippingAddressRepo;
    private final StatusRepo statusRepo;

    @Autowired
    public UserCreator(UserRepo userRepo, PasswordEncoder passwordEncoder, ShippingAddressRepo shippingAddressRepo, StatusRepo statusRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.shippingAddressRepo = shippingAddressRepo;
        this.statusRepo = statusRepo;
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


        List<Status> stats = new LinkedList<>();
        stats.add(new Status(
                "0",
                "Очікує підтвердження",
                "Ваше замовлення створено, та чекає на підтвердження зі сторони продавця"));
        stats.add(new Status(
                "1",
                "Підтверджено",
                "Ваше замовлення підтверджено, продавець на даний момент обробляє замовлення, скоро воно буде відправлено"));
        stats.add(new Status(
                "1",
                "Підтверджено",
                "Ваше замовлення підтверджено, продавець на даний момент обробляє замовлення, скоро воно буде відправлено"));
        stats.add(new Status(
                "2",
                "Відправлено",
                "Продавець відправив замовлення, воно прямує по зазначеній у замовленні адресі"));
        stats.add(new Status(
                "3",
                "Отримано",
                "Покупець отримав замовлення, та підтвердив факт отримання"));
        stats.add(new Status(
                "4",
                "Отримано",
                "Покупець отримав замовлення, та підтвердив факт отримання"));
        stats.add(new Status(
                "b0",
                "Скасовано",
                "Замовлення скасовано"));

        statusRepo.saveAll(stats);


//        ShippingAddress shippingAddress = new ShippingAddress();
//        shippingAddress.setUser(admin);
//        shippingAddress.setRecipientFullName("Admin Admin Admin");
//        shippingAddress.setRecipientMobileNumber("0648484842");
//        shippingAddress.setRegion("Lvivska obl.");
//        shippingAddress.setCity("Lviv");
//        shippingAddress.setStreet("Bohdana Hmelnitskogo");
//        shippingAddress.setBuildingNumber("32 A");
//        shippingAddress.setOfficeNumber("12");
//        shippingAddress.setPostCode("79900");
//        shippingAddressRepo.save(shippingAddress);
    }
}

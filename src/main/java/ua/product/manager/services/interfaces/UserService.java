package ua.product.manager.services.interfaces;

import ua.product.manager.models.User;

import java.util.List;

public interface UserService {

    void registerUser(User user);

    List<User> getUsersList();

}

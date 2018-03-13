package ua.product.manager.services.interfaces;

import ua.product.manager.models.User;

public interface UserService {

    boolean registerUser(User user);
    void autologin();
    boolean checkUser(User user);
    User getFullData(User user);
}

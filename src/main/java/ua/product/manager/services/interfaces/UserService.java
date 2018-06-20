package ua.product.manager.services.interfaces;

import ua.product.manager.models.Order;
import ua.product.manager.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    void registerUser(HttpServletRequest request, User user);

    List<User> getUsersList();

    User getUserById(int id);

    User getUserByUsername(String username);

    String getUserRole();
}

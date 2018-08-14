package ua.product.manager.services.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Order;
import ua.product.manager.models.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public interface UserService {

    void registerUser(HttpServletRequest request, User user);

    @Secured("ROLE_ADMIN")
    List<User> getUsersList();

    @Secured("ROLE_ADMIN")
    User getUserById(int id);

    @Secured("ROLE_USER")
    User getUserByUsername(String username);

    String getUserRole();

    Boolean checkUsername(String username);

    Boolean checkUserEmail(String email);

    Boolean checkUserTel(String tel);
}

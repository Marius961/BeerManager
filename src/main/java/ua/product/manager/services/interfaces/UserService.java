package ua.product.manager.services.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Order;
import ua.product.manager.models.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public interface UserService {

    void registerUser(HttpServletRequest request, User user);

    Boolean checkUsername(String username);

    Boolean checkUserEmail(String email);
}

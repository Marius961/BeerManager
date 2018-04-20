package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.User;

import java.util.List;

public interface UserDAO {

    @Secured("ROLE_ADMIN")
    List<User> getAllUsers();

    User getUseById(int userId);

    void insertUser(User user);

    void updateUser(User user);

    @Secured("ROLE_ADMIN")
    void removeUser(int userId);

    @Secured("ROLE_ADMIN")
    void blockUser(int userId);

    void unblockUser(int userId);

    void setUserGroup(int userId, int groupId);
}

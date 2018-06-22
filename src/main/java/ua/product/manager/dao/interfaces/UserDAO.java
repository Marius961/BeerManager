package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.User;

import java.util.List;

public interface UserDAO {

    @Secured("ROLE_ADMIN")
    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserById(int userId);

    void insertUser(User user);

    Boolean checkUsername(String username);

    Boolean checkUserEmail(String email);

    Boolean checkUserTel(String tel);

    void updateUser(User user);

    @Secured("ROLE_ADMIN")
    void removeUser(int userId);

    @Secured("ROLE_ADMIN")
    void blockUser(int userId);

    void unblockUser(int userId);

    @Secured("ROLE_ADMIN")
    void updateUserGroup(String username, int groupId);

    void addUserToGroup(String username, int groupId);

    void removeUserFromGroup(String username);
}

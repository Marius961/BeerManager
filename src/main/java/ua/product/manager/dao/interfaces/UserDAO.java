package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserById(int userId);

    void insertUser(User user);

    Boolean checkUsername(String username);

    Boolean checkUserEmail(String email);

    Boolean checkUserTel(String tel);

    void updateUser(User user);

    void removeUser(int userId);

    void blockUser(int userId);

    void unblockUser(int userId);

    void updateUserGroup(String username, int groupId);

    void addUserToGroup(String username, int groupId);

    void removeUserFromGroup(String username);
}

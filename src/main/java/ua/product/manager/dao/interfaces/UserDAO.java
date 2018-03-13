package ua.product.manager.dao.interfaces;

import ua.product.manager.models.User;

public interface UserDAO {
    User checkUser(int telnumber, String password);

    int insertUser(
            String fullName,
            String companyName,
            String companyAddress,
            String email,
            int telNumber,
            String password
    );

    void insertUser(User user);
    User getFullUserData(int userId);

}

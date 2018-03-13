package ua.product.manager.dao.interfaces;

import ua.product.manager.models.User;

public interface UserDAO {
    User checkUser(String telnumber, String password);

    int insertUser(
            String fullName,
            String companyName,
            String companyAddress,
            String email,
            String telNumber,
            String password
    );

    void insertUser(User user);
    User getFullUserData(int userId);

    boolean checkUserTelNumber(String telnumber);
}

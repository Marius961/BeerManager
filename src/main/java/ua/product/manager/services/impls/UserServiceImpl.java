package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public boolean registerUser(User user) {
        if(user != null) {
            userDAO.insertUser(user);
            return true;
        } else return false;
    }

    @Override
    public void autologin() {

    }

    @Override
    public boolean checkUser(User user) {
        if (userDAO.checkUser(user.getTelNumber(), user.getPassword()) != null) {
            return true;
        } else return false;
    }

    @Override
    public User getFullData(User user) {
        return null;
    }


}

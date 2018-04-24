package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.insertUser(user);
    }
}

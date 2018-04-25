package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

import java.util.Collection;
import java.util.List;

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

    @Override
    public List<User> getUsersList() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUseById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public String getUserRole() {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
            }
        }
        if (isAdmin) return "ROLE_ADMIN";
        if (isUser) return "ROLE_USER";
        else return null;
    }
}

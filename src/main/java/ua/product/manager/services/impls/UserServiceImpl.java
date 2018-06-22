package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    private void setComponents(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void registerUser(HttpServletRequest request, User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.insertUser(user);
        authWithAuthManager(request, user.getUsername(), password);
    }

    @Override
    public List<User> getUsersList() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
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

    @Override
    public Boolean checkUsername(String username) {
        return userDAO.checkUsername(username);
    }

    @Override
    public Boolean checkUserEmail(String email) {
        return userDAO.checkUserEmail(email);
    }

    @Override
    public Boolean checkUserTel(String tel) {
        return userDAO.checkUserTel(tel);
    }

    private void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

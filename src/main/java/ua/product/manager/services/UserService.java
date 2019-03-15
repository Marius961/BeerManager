package ua.product.manager.services;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.Role;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.UserRegistrationFailedException;
import ua.product.manager.repo.UserRepo;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepository;


    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> opUser = userRepository.findByUsername(s);
        if (!opUser.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        return opUser.get();
    }

    public void createUser(User user) throws UserRegistrationFailedException {
        if (!userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            try {
                userRepository.save(user);
            } catch (HibernateException e) {
                throw new UserRegistrationFailedException("Failed to register new user.");
            }
        } else throw new UserRegistrationFailedException("Failed to register new user, because username or email already exist.");

    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void setAsAdmin(Long userId) {
        Optional opUser = userRepository.findById(userId);
        if (opUser.isPresent()) {
            User user = (User) opUser.get();
            user.addRole(Role.ADMIN);
            System.out.println(user.getRoles());
            userRepository.save(user);
        }
    }

    public boolean isRegistered(String username) throws HibernateException {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}

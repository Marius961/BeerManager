package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.User;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findFirstByUsernameOrEmail(String username, String email);
}

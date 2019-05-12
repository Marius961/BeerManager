package ua.product.manager.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.User;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);

    @Query("SELECT count(u) FROM User u WHERE u.email = :email AND  u.id <> :id")
    int countUserByEmailAndIdNot(String email, Long id);
}

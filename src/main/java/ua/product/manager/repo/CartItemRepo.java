package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.product.manager.entities.CartItem;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.User;

import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem, Long> {

    boolean existsByIdAndUserId(Long itemId, Long userId);

    Optional<CartItem> findByIdAndUserId(Long itemId, Long userId);

    Iterable<CartItem> findAllByUserId(Long userId);

    void deleteByIdAndUserId(Long cartItemId, Long userId);

    void deleteAllByProduct(Product product);

    Optional<CartItem> findByProductIdAndUserId(Long productId, Long userId);
}

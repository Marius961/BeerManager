package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.product.manager.entities.CartItem;

import java.util.Optional;

@Repository
public interface CartItemRepo extends CrudRepository<CartItem, Long> {

    Optional<CartItem> findByIdAndUserId(Long itemId, Long userId);

    Iterable<CartItem> findAllByUserId(Long userId);

    void deleteByIdAndUserId(Long cartItemId, Long userId);

    void deleteByProductId(Long productId);

    Optional<CartItem> findByProductIdAndUserId(Long productId, Long userId);
}

package ua.product.manager.services.interfaces;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import ua.product.manager.models.Order;
import ua.product.manager.models.Product;

import java.util.List;
import java.util.Map;

public interface OrderService {

    @Secured("ROLE_USER")
    void createOrder(Order order);

    void blockProduct(int productId);

    void unblockProduct(int productId);

    void removeProduct(int productId);

    List<Product> getAllProducts();

    void addProduct(Product product);
}

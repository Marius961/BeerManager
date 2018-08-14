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

    @Secured("ROLE_ADMIN")
    Map<String, List<Order>> getOrdersWithUserDataByDate(String date);

    @Secured("ROLE_USER")
    Map<String, List<Order>> getOrdersByUsernameAndDate(String username, String date);

    @Secured("ROLE_USER")
    Order getNewOrder();

    @Secured("ROLE_ADMIN")
    void blockProduct(int productId);

    @Secured("ROLE_ADMIN")
    void unblockProduct(int productId);

    @Secured("ROLE_ADMIN")
    void removeProduct(int productId);

    @Secured("ROLE_ADMIN")
    List<Product> getAllProducts();

    @Secured("ROLE_ADMIN")
    void addProduct(Product product);

    @Secured("ROLE_ADMIN")
    void removeOrder(int orderId);
}

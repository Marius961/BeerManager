package ua.product.manager.services.interfaces;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import ua.product.manager.models.Order;
import ua.product.manager.models.Product;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createOrder(Order order);


    Order getOrderByUserId(int userId);

    @Secured("ROLE_ADMIN")
    Map<String, List<Order>> getOrdersWithUserDataByDate(String date);

    Map<String, List<Order>> getOrders(int userId);

    Map<String, List<Order>> getOrders(String username);

    List<Product> getActiveProducts();

    List<Product> getNotActiveProducts();

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
    void removeOrder(int orderId);
}

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

    Map<String, List<Order>> getOrdersWithUserDataByDate(String date);

    Map<String, List<Order>> getOrders(int userId);

    Map<String, List<Order>> getOrdersByUsernameAndDate(String username, String date);

    Order getNewOrder();

    void blockProduct(int productId);

    void unblockProduct(int productId);

    void removeProduct(int productId);

    List<Product> getAllProducts();

    void addProduct(Product product);

    void removeOrder(int orderId);
}

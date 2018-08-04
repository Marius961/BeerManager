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

    Map<String, List<Order>> getOrders(String username);

    List<Product> getActiveProducts();

    List<Product> getNotActiveProducts();

    Order getNewOrder();

    void blockProduct(int productId);

    void unblockProduct(int productId);

    void removeProduct(int productId);

    List<Product> getAllProducts();

    List<Product> searchProduct(String request);

    void addProduct(Product product);

    void removeOrder(int orderId);
}

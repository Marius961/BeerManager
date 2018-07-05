package ua.product.manager.services.interfaces;

import org.springframework.transaction.annotation.Transactional;
import ua.product.manager.models.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createOrder(Order order);


    Order getOrderByUserId(int userId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(int userId);

    Map<String, List<Order>> getOrdersByUsername(String username);

    Order getNewOrder();

    void removeOrder(int orderId);
}

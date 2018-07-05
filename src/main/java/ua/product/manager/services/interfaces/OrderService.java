package ua.product.manager.services.interfaces;

import org.springframework.transaction.annotation.Transactional;
import ua.product.manager.models.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);


    Order getOrderByUserId(int userId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrdersByUsername(String username);

    Order getNewOrder();

    void removeOrder(int orderId);
}

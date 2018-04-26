package ua.product.manager.services.interfaces;

import org.springframework.transaction.annotation.Transactional;
import ua.product.manager.models.Order;

import java.util.List;

public interface OrderService {

    @Transactional
    void createOrder(Order order);

    Order getOrderByUserId(int userId);

    List<Order> getAllOrdersByUserId(int userId);

    Order getNewOrder();
}

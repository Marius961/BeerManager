package ua.product.manager.services.interfaces;

import ua.product.manager.models.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    Order getOrderByUserId(int userId);

    List<Order> getAllOrdersByUserId(int userId);
}

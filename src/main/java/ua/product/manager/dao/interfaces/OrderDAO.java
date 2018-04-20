package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> getOrdersByUserId(int userId);

    Order getOrder(int orderId);

    void createOrder(Order order);

    @Secured("ROLE_ADMIN")
    void updateOrder(Order order);

    @Secured("ROLE_ADMIN")
    void removeOrder(int orderId);
}

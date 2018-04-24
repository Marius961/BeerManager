package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;

import java.util.List;

public interface OrderDAO {

    List<Order> getOrdersByUserId(int userId);

    List<OrderItem> getItemsByOrderId(int orderId);

    void addItemToOrder(OrderItem orderItem);

    void deleteItem(int itemId);

    Order getOrder(int orderId);

    void createOrder(Order order);

    @Secured("ROLE_ADMIN")
    void updateOrder(Order order);

    @Secured("ROLE_ADMIN")
    void removeOrder(int orderId);
}

package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;

import java.util.List;

public interface OrderDAO {

    @Secured("ROLE_ADMIN")
    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrdersByUserName(String username);

    List<OrderItem> getItemsByOrderId(int orderId);

    void addItemToOrder(OrderItem orderItem);

    @Secured("ROLE_ADMIN")
    void deleteItem(int itemId);

    Order getOrder(int orderId);

    int createOrder(Order order);

    @Secured("ROLE_ADMIN")
    void updateOrder(Order order);

    @Secured("ROLE_ADMIN")
    void removeOrder(int orderId);
}
